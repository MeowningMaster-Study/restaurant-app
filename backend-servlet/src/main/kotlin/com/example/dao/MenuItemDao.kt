package com.example.dao

import com.example.SqlService
import com.example.models.MenuItemModel
import java.math.BigDecimal


object MenuItemDao {
    fun create(item: MenuItemModel) {
        val sql = "INSERT INTO menu_item (name, price) VALUES (?, ?)"
        SqlService.getConnection().prepareStatement(sql).use { stmt ->
            stmt.setString(1, item.name)
            stmt.setBigDecimal(2, item.price)
            stmt.executeUpdate()
        }
    }
    
    fun read(id: Int): MenuItemModel? {
        val sql = "SELECT * FROM menu_item WHERE id = ?"
        SqlService.getConnection().prepareStatement(sql).use { stmt ->
            stmt.setInt(1, id)
            stmt.executeQuery().use { rs ->
                if (rs.next()) {
                    val name: String = rs.getString("name")
                    val price: BigDecimal = rs.getBigDecimal("price")
                    return MenuItemModel(id, name, price)
                }
            }
        }
        return null
    }
    
    fun update(item: MenuItemModel) {
        val sql = "UPDATE menu_item SET name = ?, price = ? WHERE id = ?"
        SqlService.getConnection().prepareStatement(sql).use { stmt ->
            stmt.setString(1, item.name)
            stmt.setBigDecimal(2, item.price)
            stmt.setInt(3, item.id)
            stmt.executeUpdate()
        }
    }
    
    fun delete(id: Int) {
        val sql = "DELETE FROM menu_item WHERE id = ?"
        SqlService.getConnection().prepareStatement(sql).use { stmt ->
            stmt.setInt(1, id)
            stmt.executeUpdate()
        }
    }
    
    fun findAll(): List<MenuItemModel> {
        val items: MutableList<MenuItemModel> = ArrayList<MenuItemModel>()
        val sql = "SELECT * FROM menu_item"
        SqlService.getConnection().createStatement().use { stmt ->
            stmt.executeQuery(sql).use { rs ->
                while (rs.next()) {
                    val id: Int = rs.getInt("id")
                    val name: String = rs.getString("name")
                    val price: BigDecimal = rs.getBigDecimal("price")
                    val item = MenuItemModel(id, name, price)
                    items.add(item)
                }
            }
        }
        return items
    }
}

