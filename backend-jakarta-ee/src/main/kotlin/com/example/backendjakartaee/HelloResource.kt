package com.example.backendjakartaee

import com.example.backendjakartaee.dao.MenuItemDao.findAll
import com.google.gson.Gson
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces


@Path("/hello-world")
class HelloResource {
    @GET
    @Produces("text/plain")
    fun hello(): String {
        val items = findAll()
        val json = Gson().toJson(items)
        return json
    }
}