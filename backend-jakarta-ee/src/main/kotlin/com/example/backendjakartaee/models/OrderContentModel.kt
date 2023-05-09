package com.example.backendjakartaee.models

import com.example.backendjakartaee.JsonSerializable

data class OrderContentModel(var orderId: Int, var menuItem: MenuItemModel, var quantity: Int) : JsonSerializable
