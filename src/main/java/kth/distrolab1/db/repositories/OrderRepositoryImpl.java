package kth.distrolab1.db.repositories;


import kth.distrolab1.bo.entities.Order;
import kth.distrolab1.bo.entities.OrderItem;
import kth.distrolab1.db.DBManager;

import java.sql.*;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public Order createOrder(Order order) {
        ResultSet generatedKeys;
        PreparedStatement orderInsertStatement = null;
        PreparedStatement orderItemInsertStatement = null;
        PreparedStatement updateItemQuantityStatement = null;

        try {
            Connection con = DBManager.getConnection();
            con.setAutoCommit(false);  // Start an order

            // Insert the user into the 'users' table
            String orderQuery = "INSERT INTO orders (user_id, purchase_date, amount) VALUES (?, ?, ?)";
            orderInsertStatement = con.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);
            orderInsertStatement.setInt(1, order.getUserId());
            orderInsertStatement.setDate(2, new java.sql.Date(order.getPurchaseDate().getTime()));
            orderInsertStatement.setDouble(3, order.getAmount());

            int rowsAffected = orderInsertStatement.executeUpdate();

            if (rowsAffected <= 0) {
                con.rollback();  // Roll back the order if user creation fails
                throw new IllegalArgumentException("Order creation failed");
            }

            // Get the generated user ID
            generatedKeys = orderInsertStatement.getGeneratedKeys();
            int orderId;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            } else {
                con.rollback();
                throw new IllegalArgumentException("Order creation failed to obtain user ID");
            }

            // Insert role assignments into 'items_in_order' table
            String orderItemQuery = "INSERT INTO items_in_order (order_id, item_id, item_name, quantity, total_price) VALUES (?, ?, ?, ?, ?)";
            orderItemInsertStatement = con.prepareStatement(orderItemQuery);

            for (OrderItem orderItem : order.getItemsBought()) {
                orderItemInsertStatement.setInt(1, orderId);
                orderItemInsertStatement.setInt(2, orderItem.getItemId());
                orderItemInsertStatement.setString(3, orderItem.getItemName());
                orderItemInsertStatement.setInt(4, orderItem.getQuantity());
                orderItemInsertStatement.setDouble(5, orderItem.getTotalPrice());
                orderItemInsertStatement.addBatch();  // Add role assignments as a batch
            }

            orderItemInsertStatement.executeBatch();  // Execute the batch

            // Update item quantities in the 'items' table
            String updateItemQuantityQuery = "UPDATE items SET quantity = quantity - ? WHERE id = ?";
            updateItemQuantityStatement = con.prepareStatement(updateItemQuantityQuery);
            for (OrderItem orderItem : order.getItemsBought()) {
                updateItemQuantityStatement.setInt(1, orderItem.getQuantity());
                updateItemQuantityStatement.setInt(2, orderItem.getItemId());
                updateItemQuantityStatement.addBatch();  // Add the update statements as a batch
            }

            int[] updateCounts = updateItemQuantityStatement.executeBatch();  // Execute the batch
            for (int updateCount : updateCounts) {
                if (updateCount < 0) {
                    con.rollback();  // Roll back the order if item quantity goes below 0
                    throw new IllegalArgumentException("Item quantity became negative");
                }
            }

            con.commit();  // Commit the order
            order.setOrderId(orderId);
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (orderInsertStatement != null) {
                    orderInsertStatement.close();
                }
                if (orderItemInsertStatement != null) {
                    orderItemInsertStatement.close();
                }
                if (updateItemQuantityStatement != null) {
                    updateItemQuantityStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Order findOrderById(int orderId) {
        return null;
    }

    @Override
    public List<Order> findOrdersForUser(int userId) {
        return null;
    }

    @Override
    public Order updateOrder(Order order) {
        return null;
    }

    @Override
    public void deleteOrder(int orderId) {

    }

    @Override
    public List<Order> findAllOrder() {
        return null;
    }
}
