package com.example.backendjakarta.dao;

import com.example.backendjakarta.SQLService;
import com.example.backendjakarta.models.OrderModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    // CREATE operation
    public void addOrder(OrderModel order) throws SQLException {
        String sql = "INSERT INTO \"order\" (client_name) VALUES (?)";

        try (PreparedStatement statement = SQLService.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, order.getClientName());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        }
    }

    // READ operation
    public List<OrderModel> getAllOrders() throws SQLException {
        String sql = "SELECT * FROM \"order\"";
        List<OrderModel> orders = new ArrayList<>();

        try (PreparedStatement statement = SQLService.getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                OrderModel order = new OrderModel();
                order.setId(resultSet.getInt("id"));
                order.setClientName(resultSet.getString("client_name"));
                orders.add(order);
            }
        }

        return orders;
    }

    public OrderModel getOrderById(int id) throws SQLException {
        String sql = "SELECT * FROM \"order\" WHERE id = ?";
        OrderModel order = null;

        try (PreparedStatement statement = SQLService.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    order = new OrderModel();
                    order.setId(resultSet.getInt("id"));
                    order.setClientName(resultSet.getString("client_name"));
                }
            }
        }

        return order;
    }

    // UPDATE operation
    public void updateOrder(OrderModel order) throws SQLException {
        String sql = "UPDATE \"order\" SET client_name = ? WHERE id = ?";

        try (PreparedStatement statement = SQLService.getConnection().prepareStatement(sql)) {
            statement.setString(1, order.getClientName());
            statement.setInt(2, order.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating order failed, no rows affected.");
            }
        }
    }

    // DELETE operation
    public void deleteOrder(int id) throws SQLException {
        String sql = "DELETE FROM \"order\" WHERE id = ?";

        try (PreparedStatement statement = SQLService.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting order failed, no rows affected.");
            }
        }
    }
}
