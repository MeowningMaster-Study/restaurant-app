package com.example.models


data class OrderContentCreateModel(
     var menuItemId: Int,
     var quantity: Int
)

data class OrderContentModel(
    var orderId: Int,
    var menuItemId: Int,
    var quantity: Int
)