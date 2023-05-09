package com.example.backendjakarta.dao;

import com.example.backendjakarta.SQLService;
import com.example.backendjakarta.models.MenuItemModel;
import lombok.SneakyThrows;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDao {

    @SneakyThrows
    public static void create(MenuItemModel item) {
        String sql = "INSERT INTO menu_item (name, price) VALUES (?, ?)";
        try (PreparedStatement stmt = SQLService.getConnection().prepareStatement(sql)) {
            stmt.setString(1, item.getName());
            stmt.setBigDecimal(2, item.getPrice());
            stmt.executeUpdate();
        }
    }

    @SneakyThrows
    public static MenuItemModel read(int id) {
        String sql = "SELECT * FROM menu_item WHERE id = ?";
        try (PreparedStatement stmt = SQLService.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    BigDecimal price = rs.getBigDecimal("price");
                    return new MenuItemModel(id, name, price);
                }
            }
        }
        return null;
    }

    @SneakyThrows
    public static void update(MenuItemModel item) {
        String sql = "UPDATE menu_item SET name = ?, price = ? WHERE id = ?";
        try (PreparedStatement stmt = SQLService.getConnection().prepareStatement(sql)) {
            stmt.setString(1, item.getName());
            stmt.setBigDecimal(2, item.getPrice());
            stmt.setInt(3, item.getId());
            stmt.executeUpdate();
        }
    }

    @SneakyThrows
    public static void delete(int id) {
        String sql = "DELETE FROM menu_item WHERE id = ?";
        try (PreparedStatement stmt = SQLService.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }


    @SneakyThrows
    public static List<MenuItemModel> findAll() {
        List<MenuItemModel> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_item";
        try (Statement stmt = SQLService.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                MenuItemModel item = new MenuItemModel(id, name, price);
                items.add(item);
            }
        }
        return items;
    }
}
