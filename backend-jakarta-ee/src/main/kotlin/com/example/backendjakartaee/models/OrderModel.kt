package com.example.backendjakartaee.models

import jakarta.json.bind.annotation.JsonbCreator
import jakarta.json.bind.annotation.JsonbProperty

data class OrderCreateModel @JsonbCreator constructor(
    @JsonbProperty("clientName") var clientName: String,
    @JsonbProperty("paid") var paid: Boolean
)

data class OrderModel @JsonbCreator constructor(
    @JsonbProperty("id") var id: Int,
    @JsonbProperty("clientName") var clientName: String,
    @JsonbProperty("paid") var paid: Boolean
)