package com.example.backendjakartaee

import com.google.gson.Gson
import com.google.gson.GsonBuilder


interface JsonSerializable {
    fun toJson(): String? {
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(this)
    }
}