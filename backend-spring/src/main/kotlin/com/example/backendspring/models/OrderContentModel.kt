package com.example.backendspring.models

data class OrderContentCreateModel constructor(
    var menuItemId: Int,
    var quantity: Int
)

data class OrderContentModel constructor(
    var orderId: Int,
    var menuItemId: Int,
    var quantity: Int
)