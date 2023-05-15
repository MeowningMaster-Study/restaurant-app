package com.example.backendjakartaee.controllers

import com.example.backendjakartaee.dao.MenuItemDao.findAll
import com.google.gson.Gson
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/menu-items")
class MenuItemsController {
    @GET
    fun get(): String {
        val items = findAll()
        return Gson().toJson(items)
    }
}