package com.example.dao

import com.example.SqlService
import com.example.models.OrderContentCreateModel
import com.example.models.OrderContentModel

object OrderContentDao {
    fun create(orderId: Int, item: OrderContentCreateModel) {
        val sql = "INSERT INTO order_content (order_id, menu_item, quantity) VALUES (?, ?, ?)"
        SqlService.getConnection().prepareStatement(sql).use { stmt ->
            stmt.setInt(1, orderId)
            stmt.setInt(2, item.menuItemId)
            stmt.setInt(3, item.quantity)
            stmt.executeUpdate()
        }
    }

    fun findManyByOrder(orderId: Int): List<OrderContentModel> {
        val sql = "SELECT * FROM order_content WHERE order_id = ?"
        val contents: MutableList<OrderContentModel> = ArrayList()
        SqlService.getConnection().prepareStatement(sql).use { statement ->
            statement.setInt(1, orderId)
            statement.executeQuery().use { resultSet ->
                while (resultSet.next()) {
                    val menuItemId = resultSet.getInt("menu_item")
                    val quantity = resultSet.getInt("quantity")
                    val content = OrderContentModel(orderId, menuItemId, quantity)
                    contents.add(content)
                }
            }
        }
        return contents
    }

    fun deleteByOrder(orderId: Int) {
        val sql = "DELETE FROM order_content WHERE order_id = ?"
        SqlService.getConnection().prepareStatement(sql).use { statement ->
            statement.setInt(1, orderId)
            statement.executeUpdate()
        }
    }
}