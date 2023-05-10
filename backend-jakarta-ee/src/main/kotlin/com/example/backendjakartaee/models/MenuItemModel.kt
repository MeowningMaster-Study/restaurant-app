package com.example.backendjakartaee.models

import com.example.backendjakartaee.JsonSerializable
import java.math.BigDecimal

data class MenuItemModel(var id: Int, var name: String, var price: BigDecimal) : JsonSerializable