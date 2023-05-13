package com.example.backendspring.models

data class OrderCreateModel constructor(
    var clientName: String,
    var paid: Boolean
)

data class OrderModel constructor(
    var id: Int,
    var clientName: String,
    var paid: Boolean
)