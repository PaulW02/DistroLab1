package kth.distrolab1.db.repositories;


import kth.distrolab1.bo.entities.Role;
import kth.distrolab1.bo.entities.User;
import kth.distrolab1.db.DBManager;

import java.sql.*;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{

    public User findByUsernameAndPassword(String username, String password){
        User user = null;

        try{
            Connection con = DBManager.getConnection();
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1,username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                int userId= rs.getInt("id");
                String foundUsername = rs.getString("username");
                String foundPassword = rs.getString("password");
                String email = rs.getString("email");
                String fullName = rs.getString("full_name");
                Date registrationDate = rs.getDate("registration_date");

                user = new User(userId, foundUsername, foundPassword, email, fullName, registrationDate);
            }
            rs.close();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public User createUser(String username, String password, String email, String fullname, java.util.Date registrationDate, List<Role> roles) {
        ResultSet generatedKeys;
        PreparedStatement userInsertStatement = null;
        PreparedStatement roleInsertStatement = null;

        try {
            Connection con = DBManager.getConnection();
            con.setAutoCommit(false);  // Start a transaction

            // Insert the user into the 'users' table
            String userQuery = "INSERT INTO users (username, password, email, full_name, registration_date) VALUES (?, ?, ?, ?, ?)";
            userInsertStatement = con.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            userInsertStatement.setString(1, username);
            userInsertStatement.setString(2, password);
            userInsertStatement.setString(3, email);
            userInsertStatement.setString(4, fullname);
            userInsertStatement.setDate(5, new java.sql.Date(registrationDate.getTime()));

            int rowsAffected = userInsertStatement.executeUpdate();

            if (rowsAffected <= 0) {
                con.rollback();  // Roll back the transaction if user creation fails
                throw new IllegalArgumentException("User creation failed");
            }

            // Get the generated user ID
            generatedKeys = userInsertStatement.getGeneratedKeys();
            int userId;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            } else {
                con.rollback();
                throw new IllegalArgumentException("User creation failed to obtain user ID");
            }

            // Insert role assignments into 'user_roles' table
            String roleQuery = "INSERT INTO user_roles (user_id, role_id) VALUES (?, (SELECT role_id FROM roles WHERE role_name = ?))";
            roleInsertStatement = con.prepareStatement(roleQuery);

            for (Role role : roles) {
                roleInsertStatement.setInt(1, userId);
                roleInsertStatement.setString(2, role.getRoleName());
                roleInsertStatement.addBatch();  // Add role assignments as a batch
            }

            roleInsertStatement.executeBatch();  // Execute the batch

            con.commit();  // Commit the transaction

            // User was successfully created with assigned roles
            return new User(userId, username, password, email, fullname, registrationDate);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (userInsertStatement != null) {
                    userInsertStatement.close();
                }
                if (roleInsertStatement != null) {
                    roleInsertStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
