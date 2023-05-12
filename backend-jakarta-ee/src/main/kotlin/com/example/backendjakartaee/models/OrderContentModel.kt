package com.example.backendjakartaee.models

import jakarta.json.bind.annotation.JsonbCreator
import jakarta.json.bind.annotation.JsonbProperty

data class OrderContentModel @JsonbCreator constructor(
    @JsonbProperty("orderId") var orderId: Int,
    @JsonbProperty("menuItem") var menuItem: MenuItemModel,
    @JsonbProperty("quantity") var quantity: Int
)