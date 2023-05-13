package com.example.backendspring.services

import com.example.backendspring.models.MenuItemModel
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class MenuItemService(private val jdbcTemplate: JdbcTemplate) {
    fun create(item: MenuItemModel) {
        val sql = "INSERT INTO menu_item (name, price) VALUES (?, ?)"
        jdbcTemplate.update(sql, item.name, item.price)
    }

    fun read(id: Int): MenuItemModel? {
        val sql = "SELECT * FROM menu_item WHERE id = ?"
        return jdbcTemplate.query(sql, RowMapper() { rs, _ ->
            val name: String = rs.getString("name")
            val price: BigDecimal = rs.getBigDecimal("price")
            MenuItemModel(id, name, price)
        }, id).firstOrNull()
    }

    fun update(item: MenuItemModel) {
        val sql = "UPDATE menu_item SET name = ?, price = ? WHERE id = ?"
        jdbcTemplate.update(sql, item.name, item.price, item.id)
    }

    fun delete(id: Int) {
        val sql = "DELETE FROM menu_item WHERE id = ?"
        jdbcTemplate.update(sql, id)
    }

    fun findAll(): List<MenuItemModel> {
        val sql = "SELECT * FROM menu_item"
        return jdbcTemplate.query(sql, RowMapper { rs, _ ->
            val id: Int = rs.getInt("id")
            val name: String = rs.getString("name")
            val price: BigDecimal = rs.getBigDecimal("price")
            MenuItemModel(id, name, price)
        })
    }
}

