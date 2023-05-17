package com.example.models


data class OrderCreateModel(
    var clientName: String,
    var paid: Boolean
)

data class OrderModel(
    var id: Int,
    var clientName: String,
    var paid: Boolean
)