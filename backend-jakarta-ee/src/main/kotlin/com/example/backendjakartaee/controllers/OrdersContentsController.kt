package com.example.backendjakartaee.controllers

import com.example.backendjakartaee.dao.OrderContentDao
import com.example.backendjakartaee.dao.OrderDao
import com.example.backendjakartaee.models.OrderContentCreateModel
import com.example.backendjakartaee.models.OrderContentModel
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam

@Path("/orders/{orderId}/contents")
class OrdersContentsController {
    @GET
    fun get(@PathParam("orderId") orderId: Int): List<OrderContentModel> {
        return OrderContentDao.findManyByOrder(orderId)
    }

    @DELETE
    fun delete(@PathParam("orderId") orderId: Int) {
        OrderContentDao.deleteByOrder(orderId)
        OrderDao.deleteOrder(orderId)
    }

    @PUT
    fun put(@PathParam("orderId") orderId: Int, contents: List<OrderContentCreateModel>) {
        OrderContentDao.deleteByOrder(orderId)
        contents.forEach {
            OrderContentDao.create(orderId, it)
        }
    }
}