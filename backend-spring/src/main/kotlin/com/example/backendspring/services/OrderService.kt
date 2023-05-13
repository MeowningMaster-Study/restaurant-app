package com.example.backendspring.services

import com.example.backendspring.models.OrderCreateModel
import com.example.backendspring.models.OrderModel
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Service
import java.sql.Statement

@Service
class OrderService(private val jdbcTemplate: JdbcTemplate) {
    fun addOrder(order: OrderCreateModel): Int {
        val sql = "INSERT INTO \"order\" (client_name, paid) VALUES (?, ?)"
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update({ connection ->
            val statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            statement.setString(1, order.clientName)
            statement.setBoolean(2, order.paid)
            statement
        }, keyHolder)
        return keyHolder.keyList[0]["id"] as Int
    }

    fun getAllOrders(): List<OrderModel> {
        val sql = "SELECT * FROM \"order\""
        return jdbcTemplate.query(sql) { resultSet, _ ->
            val id = resultSet.getInt("id")
            val clientName = resultSet.getString("client_name")
            val paid = resultSet.getBoolean("paid")
            OrderModel(id, clientName, paid)
        }
    }

    fun getOrderById(id: Int): OrderModel {
        val sql = "SELECT * FROM \"order\" WHERE id = ?"
        return jdbcTemplate.queryForObject(sql, OrderModel::class.java, id)!!
    }

    fun updateOrder(order: OrderModel) {
        val sql = "UPDATE \"order\" SET client_name = ?, paid = ? WHERE id = ?"
        jdbcTemplate.update(sql, order.clientName, order.paid, order.id)
    }

    fun deleteOrder(id: Int) {
        val sql = "DELETE FROM \"order\" WHERE id = ?"
        jdbcTemplate.update(sql, id)
    }
}
