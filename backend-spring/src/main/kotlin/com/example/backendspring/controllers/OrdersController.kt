package com.example.backendspring.controllers

import com.example.backendspring.models.OrderCreateModel
import com.example.backendspring.models.OrderModel
import com.example.backendspring.services.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrdersController(
    private val orderService: OrderService
) {
    @GetMapping
    fun getAllOrders(): List<OrderModel> {
        return orderService.getAllOrders()
    }

    @PostMapping
    fun createOrder(@RequestBody order: OrderCreateModel): Int {
        return orderService.addOrder(order)
    }

    @PutMapping
    fun updateOrder(@RequestBody order: OrderModel) {
        orderService.updateOrder(order)
    }
}
