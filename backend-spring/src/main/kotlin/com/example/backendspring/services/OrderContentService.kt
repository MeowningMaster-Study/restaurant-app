package com.example.backendspring.services

import com.example.backendspring.models.OrderContentCreateModel
import com.example.backendspring.models.OrderContentModel
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class OrderContentService(private val jdbcTemplate: JdbcTemplate) {
    fun create(orderId: Int, item: OrderContentCreateModel) {
        val sql = "INSERT INTO order_content (order_id, menu_item, quantity) VALUES (?, ?, ?)"
        jdbcTemplate.update(sql, orderId, item.menuItemId, item.quantity)
    }

    fun findManyByOrder(orderId: Int): List<OrderContentModel> {
        val sql = "SELECT * FROM order_content WHERE order_id = ?"
        return jdbcTemplate.query(sql, RowMapper { resultSet, _ ->
            val menuItemId = resultSet.getInt("menu_item")
            val quantity = resultSet.getInt("quantity")
            OrderContentModel(orderId, menuItemId, quantity)
        }, orderId)
    }

    fun deleteByOrder(orderId: Int) {
        val sql = "DELETE FROM order_content WHERE order_id = ?"
        jdbcTemplate.update(sql, orderId)
    }
}
