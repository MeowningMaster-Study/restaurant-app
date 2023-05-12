package com.example.backendjakartaee.controllers

import com.example.backendjakartaee.dao.OrderDao
import com.example.backendjakartaee.models.OrderCreateModel
import com.example.backendjakartaee.models.OrderModel
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path

@Path("/orders")
class OrdersController {
    @GET
    fun get(): List<OrderModel> {
        return OrderDao.allOrders
    }

    @POST
    fun post(order: OrderCreateModel): Int {
        return OrderDao.addOrder(order)
    }

    @PUT
    fun put(order: OrderModel) {
        OrderDao.updateOrder(order)
    }
}