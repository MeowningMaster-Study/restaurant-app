package com.example.backendjakartaee.models

import jakarta.json.bind.annotation.JsonbCreator
import jakarta.json.bind.annotation.JsonbProperty

data class OrderContentCreateModel @JsonbCreator constructor(
    @JsonbProperty("menuItem") var menuItemId: Int,
    @JsonbProperty("quantity") var quantity: Int
)

data class OrderContentModel @JsonbCreator constructor(
    @JsonbProperty("orderId") var orderId: Int,
    @JsonbProperty("menuItem") var menuItemId: Int,
    @JsonbProperty("quantity") var quantity: Int
)