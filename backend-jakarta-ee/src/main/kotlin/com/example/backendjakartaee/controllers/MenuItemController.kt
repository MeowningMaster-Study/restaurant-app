package com.example.backendjakartaee.controllers

import com.example.backendjakartaee.dao.MenuItemDao.findAll
import com.google.gson.Gson
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import java.math.BigDecimal


@Path("/menu-item")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class MenuItemController {
    @GET
    fun get(): String {
        val items = findAll()
        return Gson().toJson(items)
    }

    @POST
    fun post(data: MenuItemPost): String {
        return Gson().toJson(data)
    }
}

data class MenuItemPost(var name: String, var price: BigDecimal)