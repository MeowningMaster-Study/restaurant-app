package com.example.servlets

import com.example.dao.MenuItemDao
import com.google.gson.Gson
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "MenuItemsServlet", urlPatterns = ["/menu-items"])
class MenuItemsServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        val items = MenuItemDao.findAll()
        val reply = Gson().toJson(items)
        res.addHeader("content-type", "application/json")
        res.writer.write(reply)
    }
}