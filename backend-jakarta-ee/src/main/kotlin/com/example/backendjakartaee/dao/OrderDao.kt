package com.example.backendjakartaee.dao

import com.example.backendjakartaee.SqlService
import com.example.backendjakartaee.models.OrderCreateModel
import com.example.backendjakartaee.models.OrderModel
import java.sql.SQLException
import java.sql.Statement


object OrderDao {
    fun addOrder(order: OrderCreateModel): Int {
        val sql = "INSERT INTO \"order\" (client_name, paid) VALUES (?, ?)"
        SqlService.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { statement ->
            statement.setString(1, order.clientName)
            statement.setBoolean(2, order.paid)
            val affectedRows: Int = statement.executeUpdate()
            if (affectedRows == 0) {
                throw SQLException("Creating order failed, no rows affected.")
            }
            statement.generatedKeys.use { generatedKeys ->
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1)
                } else {
                    throw SQLException("Creating order failed, no ID obtained.")
                }
            }
        }
    }

    val allOrders: List<OrderModel>
        get() {
            val sql = "SELECT * FROM \"order\""
            val orders: MutableList<OrderModel> = ArrayList()
            SqlService.getConnection().prepareStatement(sql).use { statement ->
                statement.executeQuery().use { resultSet ->
                    while (resultSet.next()) {
                        val id = resultSet.getInt("id")
                        val clientName = resultSet.getString("client_name")
                        val paid = resultSet.getBoolean("paid")
                        val order = OrderModel(id, clientName, paid)
                        orders.add(order)
                    }
                }
            }
            return orders
        }

    fun getOrderById(id: Int): OrderModel {
        val sql = "SELECT * FROM \"order\" WHERE id = ?"
        var order: OrderModel? = null
        SqlService.getConnection().prepareStatement(sql).use { statement ->
            statement.setInt(1, id)
            statement.executeQuery().use { resultSet ->
                if (resultSet.next()) {
                    val clientName = resultSet.getString("client_name")
                    val paid = resultSet.getBoolean("client_name")
                    order = OrderModel(id, clientName, paid)
                }
            }
        }
        return order!!
    }

    fun updateOrder(order: OrderModel) {
        val sql = "UPDATE \"order\" SET client_name = ?, paid = ? WHERE id = ?"
        SqlService.getConnection().prepareStatement(sql).use { statement ->
            statement.setString(1, order.clientName)
            statement.setBoolean(2, order.paid)
            statement.setInt(3, order.id)
            val affectedRows: Int = statement.executeUpdate()
            if (affectedRows == 0) {
                throw SQLException("Updating order failed, no rows affected.")
            }
        }
    }


    fun deleteOrder(id: Int) {
        val sql = "DELETE FROM \"order\" WHERE id = ?"
        SqlService.getConnection().prepareStatement(sql).use { statement ->
            statement.setInt(1, id)
            val affectedRows: Int = statement.executeUpdate()
            if (affectedRows == 0) {
                throw SQLException("Deleting order failed, no rows affected.")
            }
        }
    }
}