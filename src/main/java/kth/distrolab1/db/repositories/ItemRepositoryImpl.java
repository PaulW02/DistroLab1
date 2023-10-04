package kth.distrolab1.db.repositories;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.db.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public List<Item> searchItems(String itemName) {
        ArrayList<Item> items = new ArrayList<>();

        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from items where name = itemName");

            while (rs.next()) {
                int i = rs.getInt("id");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                String category = rs.getString("category");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                items.add(new Item(i, name, desc, category, price, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item createItem(String itemName, String description, String category, int price, int quantity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys;
        try {
            connection = DBManager.getConnection();

            String sql = "INSERT INTO items (item_name, description, category, price, quantity) VALUES (?, ?, ?, ?, ?)";

            // Create a PreparedStatement with the SQL query
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Set the values for the p
            // laceholders in the query
            preparedStatement.setString(1, itemName);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, category);
            preparedStatement.setInt(4, price);
            preparedStatement.setInt(5, quantity);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected <= 0) {
                throw new IllegalArgumentException("User creation failed");
            }

            // Get the generated user ID
            generatedKeys = preparedStatement.getGeneratedKeys();
            int itemId;
            if (generatedKeys.next()) {
                itemId = generatedKeys.getInt(1);
            } else {
                throw new IllegalArgumentException("User creation failed to obtain user ID");
            }

            // User was successfully created with assigned roles
            return new Item(itemId, itemName, description, category, price, quantity);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL errors
            throw new RuntimeException(e);
        } finally {
            // Close the PreparedStatement (connection should not be closed here if it's shared)
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public List<Item> findAllItems() {
        List<Item> itemList = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try {
            Connection con = DBManager.getConnection();
            String query = "SELECT * FROM items";
            preparedStatement = con.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int itemId = resultSet.getInt("id"); // Assuming the ID column is named "id"
                String itemName = resultSet.getString("item_name");
                String description = resultSet.getString("description");
                String category = resultSet.getString("category");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");

                // Create an Item object for each row and add it to the itemList
                Item item = new Item(itemId, itemName, description, category, price, quantity);
                itemList.add(item);
            }

            resultSet.close();
        }catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL errors
            throw new RuntimeException(e);
        } finally {
            // Close the resources (PreparedStatement and Connection)
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return itemList;
    }

    @Override
    public Item findItemById(int itemId) {
        Item item = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Hämta en befintlig databasanslutning
            connection = DBManager.getConnection();

            // SQL-fråga för att hämta en artikel baserat på ID
            String query = "SELECT * FROM items WHERE id = ?";

            // Skapa ett PreparedStatement med SQL-frågan
            preparedStatement = connection.prepareStatement(query);

            // Ange värdet för platsmarkören i SQL-frågan
            preparedStatement.setInt(1, itemId);

            // Utför SQL-frågan och hämta resultatet
            resultSet = preparedStatement.executeQuery();

            // Kolla om det finns en matchande artikel
            if (resultSet.next()) {
                String itemName = resultSet.getString("item_name");
                String description = resultSet.getString("description");
                String category = resultSet.getString("category");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");

                // Skapa ett Item-objekt med de hämtade uppgifterna
                return new Item(itemId, itemName, description, category, price, quantity);
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hantera eventuella SQL-fel
        } finally {
            // Stäng resurserna (ResultSet, PreparedStatement och Connection)
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return item;
    }
}