package kth.distrolab1.db.repositories;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.db.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

            while (rs.next()){
                int i = rs.getInt("id");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                items.add(new Item(i, name, desc, price, quantity));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item createItem(String itemName, String description, int price, int quantity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys;
        try {
            connection = DBManager.getConnection();

            String sql = "INSERT INTO items (item_name, description, price, quantity) VALUES (?, ?, ?, ?)";

            // Create a PreparedStatement with the SQL query
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Set the values for the p
            // laceholders in the query
            preparedStatement.setString(1, itemName);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, price);
            preparedStatement.setInt(4, quantity);
