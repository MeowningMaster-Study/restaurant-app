package com.example.backendjakartaee.models

import com.example.backendjakartaee.JsonSerializable

data class OrderModel(var id: Int, var clientName: String) : JsonSerializable