package com.example.backendspring.controllers

import com.example.backendspring.models.OrderContentCreateModel
import com.example.backendspring.models.OrderContentModel
import com.example.backendspring.services.OrderContentService
import com.example.backendspring.services.OrderService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/orders/{orderId}/contents")
class OrdersContentsController(
    private val orderService: OrderService,
    private val orderContentService: OrderContentService
) {
    @CrossOrigin
    @GetMapping
    fun getOrderContents(@PathVariable orderId: Int): List<OrderContentModel> {
        return orderContentService.findManyByOrder(orderId)
    }

    @CrossOrigin
    @DeleteMapping
    fun deleteOrderContents(@PathVariable orderId: Int) {
        orderContentService.deleteByOrder(orderId)
        orderService.deleteOrder(orderId)
    }

    @CrossOrigin
    @PutMapping
    fun updateOrderContents(@PathVariable orderId: Int, @RequestBody contents: List<OrderContentCreateModel>) {
        orderContentService.deleteByOrder(orderId)
        contents.forEach {
            orderContentService.create(orderId, it)
        }
    }
}

