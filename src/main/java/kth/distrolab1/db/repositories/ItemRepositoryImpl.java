package kth.distrolab1.db.repositories;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.db.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the ItemRepository interface, providing methods to manage and process item-related operations.
 * This class interacts with the database to perform CRUD operations on items.
 */
public class ItemRepositoryImpl implements ItemRepository {

    /**
     * Searches for items based on the provided item name.
     *
     * @param itemName The name of the item to search for.
     * @return A list of items matching the provided name.
     */
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
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                byte[] imageData = rs.getBytes("imageData");
                items.add(new Item(i, name, desc, category, price, quantity, imageData));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Creates a new item with the provided details.
     *
     * @param itemName    The name of the new item.
     * @param description The description of the new item.
     * @param category    The category of the new item.
     * @param price       The price of the new item.
     * @param quantity    The quantity of the new item.
     * @param imageData   The image data of the new item.
     * @return The created item or null if creation failed.
     */
    @Override
    public Item createItem(String itemName, String description, String category, double price, int quantity, byte[] imageData) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys;
        try {
            connection = DBManager.getConnection();

            String sql = "INSERT INTO items (item_name, description, category, price, quantity, image_data) VALUES (?, ?, ?, ?, ?, ?)";

            // Create a PreparedStatement with the SQL query
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Set the values for the p
            // laceholders in the query
            preparedStatement.setString(1, itemName);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, category);
            preparedStatement.setDouble(4, price);
            preparedStatement.setInt(5, quantity);
            preparedStatement.setBytes(6, imageData);

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
            return new Item(itemId, itemName, description, category, price, quantity, imageData);
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

    /**
     * Edits an existing item with the provided details.
     *
     * @param itemId      The ID of the item to edit.
     * @param itemName    The new name of the item.
     * @param description The new description of the item.
     * @param category    The new category of the item.
     * @param price       The new price of the item.
     * @param quantity    The new quantity of the item.
     * @param imageData   The new image data of the item.
     * @return The edited item or null if editing failed.
     */
    @Override
    public Item editItem(int itemId, String itemName, String description, String category, double price, int quantity, byte[] imageData) {
        Connection connection;
        PreparedStatement preparedStatement = null;
        PreparedStatement selectStatement;
        ResultSet resultSet;

        try {
            connection = DBManager.getConnection();

            String sql = "UPDATE items SET item_name  = ?, description = ?, category = ?, price = ?, quantity = ?, image_data = ? WHERE id = ?";

            // Create a PreparedStatement with the SQL query
            preparedStatement = connection.prepareStatement(sql);

            // Set the values for the placeholders in the query
            preparedStatement.setString(1, itemName);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, category);
            preparedStatement.setDouble(4, price);
            preparedStatement.setInt(5, quantity);
            preparedStatement.setBytes(6, imageData);
            preparedStatement.setInt(7, itemId);  // Set the ID for the WHERE clause


            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were affected (item was updated)
            if (rowsAffected > 0) {
                return new Item(itemId, itemName, description, category, price, quantity, imageData);
            }
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
        return null;
    }


    /**
     * Retrieves all items from the database.
     *
     * @return A list of all items.
     */
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
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                byte[] imageData = resultSet.getBytes("image_data");

                // Create an Item object for each row and add it to the itemList
                Item item = new Item(itemId, itemName, description, category, price, quantity, imageData);
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

    /**
     * Retrieves an item based on its ID.
     *
     * @param itemId The ID of the item to retrieve.
     * @return The item with the provided ID or null if not found.
     */
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
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                byte[] imageData = resultSet.getBytes("image_data");

                // Skapa ett Item-objekt med de hämtade uppgifterna
                return new Item(itemId, itemName, description, category, price, quantity, imageData);
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