package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.User;
import kth.distrolab1.db.repositories.UserRepository;
import kth.distrolab1.db.repositories.UserRepositoryImpl;
import kth.distrolab1.ui.dtos.UserDTO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Implementation of the UserService interface, providing methods to manage and process user-related operations.
 * This class interacts with the UserRepository to perform CRUD operations on users.
 */
public class UserServiceImpl implements UserService{

    private UserRepository userRepository = new UserRepositoryImpl();

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return UserDTO representing the authenticated user or null if authentication failed.
     */
    @Override
    public UserDTO login(String username, String password) {
        if (username != null && password != null) {
            String encryptedPassword = encrypt(password);
            User user = userRepository.findByUsernameAndPassword(username, encryptedPassword);
            if (user != null) {
                return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getFullName(), user.getRoles());
            }
        }
        return null;
    }

    /**
     * Creates a new user with the provided details.
     *
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @param fullname The full name of the new user.
     * @param email The email address of the new user.
     * @param roles The roles assigned to the new user.
     * @return UserDTO representing the created user or null if creation failed.
     */
    @Override
    public UserDTO createUser(String username, String password, String fullname, String email, List<String> roles) {
        String encryptedPassword = encrypt(password);
        Date registrationDate = new Date();
        User user;
        if (username != null && password != null  && email != null && fullname != null && registrationDate != null){
            if (roles.isEmpty()){
                roles.add("role_user");
            }
            user = userRepository.createUser(username,encryptedPassword,fullname,email,registrationDate, roles);
            if (user != null){
                return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getFullName(), user.getRoles());
            }
        }
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        return userRepository.deleteUserById(userId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.getAllUsers();
        for (User user: users) {
            userDTOS.add(new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getFullName(), user.getRoles()));
        }
        return userDTOS;
    }

    /**
     * Encrypts the provided input string using the MD5 algorithm.
     *
     * @param input The string to be encrypted.
     * @return The MD5 hash of the input string in hexadecimal format or null if encryption failed.
     */
    public static String encrypt(String input) {
        try {
            // Create a MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Update the digest with the byte representation of the input string
            md.update(input.getBytes());

            // Compute the MD5 hash
            byte[] digest = md.digest();

            // Convert the byte array to a hexadecimal representation
            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(String.format("%02x", b));
            }

            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle any exceptions that might occur
            e.printStackTrace();
            return null;
        }
    }
}
