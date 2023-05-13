package com.example.backendspring.controllers

import com.example.backendspring.models.OrderContentCreateModel
import com.example.backendspring.models.OrderContentModel
import com.example.backendspring.services.OrderContentService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/orders/{orderId}/contents")
class OrdersContentsController(private val orderContentService: OrderContentService) {
    @GetMapping
    fun getOrderContents(@PathVariable orderId: Int): List<OrderContentModel> {
        return orderContentService.findManyByOrder(orderId)
    }

    @DeleteMapping
    fun deleteOrderContents(@PathVariable orderId: Int) {
        orderContentService.deleteByOrder(orderId)
    }

    @PutMapping
    fun updateOrderContents(@PathVariable orderId: Int, @RequestBody contents: List<OrderContentCreateModel>) {
        orderContentService.deleteByOrder(orderId)
        contents.forEach {
            orderContentService.create(orderId, it)
        }
    }
}

