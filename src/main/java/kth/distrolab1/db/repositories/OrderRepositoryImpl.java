package kth.distrolab1.db.repositories;


import kth.distrolab1.bo.entities.Order;
import kth.distrolab1.bo.entities.OrderItem;
import kth.distrolab1.db.DBManager;
import kth.distrolab1.ui.dtos.OrderDTO;

import java.sql.*;
import java.util.ArrayList;
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
            String orderQuery = "INSERT INTO orders (user_id, purchase_date, amount, order_sent) VALUES (?, ?, ?, ?)";
            orderInsertStatement = con.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);
            orderInsertStatement.setInt(1, order.getUserId());
            orderInsertStatement.setDate(2, new java.sql.Date(order.getPurchaseDate().getTime()));
            orderInsertStatement.setDouble(3, order.getAmount());
            orderInsertStatement.setBoolean(4, false);

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
    public List<Order> findAllOrders() {
        List<Order> orders = new ArrayList<>();
        Connection connection;
        PreparedStatement orderStatement = null;
        PreparedStatement itemStatement = null;
        ResultSet orderResultSet;
        ResultSet itemResultSet;

        try {
            connection = DBManager.getConnection();

            // Define a query to retrieve all orders
            String orderQuery = "SELECT * FROM orders";
            orderStatement = connection.prepareStatement(orderQuery);
            orderResultSet = orderStatement.executeQuery();

            while (orderResultSet.next()) {
                int orderId = orderResultSet.getInt("order_id");
                int userId = orderResultSet.getInt("user_id");
                Date purchaseDate = orderResultSet.getDate("purchase_date");
                double amount = orderResultSet.getDouble("amount");
                boolean orderSent = orderResultSet.getBoolean("order_sent");

                Order order = new Order();
                order.setOrderId(orderId);
                order.setUserId(userId);
                order.setPurchaseDate(purchaseDate);
                order.setAmount(amount);
                order.setOrderSent(orderSent);
                order.setItemsBought(new ArrayList<>());

                // Define a query to retrieve items for the current order
                String itemQuery = "SELECT * FROM items_in_order WHERE order_id = ?";
                itemStatement = connection.prepareStatement(itemQuery);
                itemStatement.setInt(1, orderId);
                itemResultSet = itemStatement.executeQuery();

                while (itemResultSet.next()) {
                    int orderItemId = itemResultSet.getInt("order_item_id");
                    int itemId = itemResultSet.getInt("item_id");
                    String itemName = itemResultSet.getString("item_name");
                    int quantity = itemResultSet.getInt("quantity");
                    double totalPrice = itemResultSet.getDouble("total_price");

                    // Create an OrderItem and add it to the current order's itemsBought list
                    OrderItem orderItem = new OrderItem(orderItemId, orderId, itemId, itemName, quantity, totalPrice);
                    order.getItemsBought().add(orderItem);
                }

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                if (orderStatement != null) {
                    orderStatement.close();
                }
                if (itemStatement != null) {
                    itemStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return orders;
    }


    @Override
    public void sendOrder(int orderId) {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(
                    "UPDATE orders SET order_sent = ? WHERE order_id = ?");
            preparedStatement.setBoolean(1, true); // Set orderSent to true
            preparedStatement.setInt(2, orderId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update orderSent");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
