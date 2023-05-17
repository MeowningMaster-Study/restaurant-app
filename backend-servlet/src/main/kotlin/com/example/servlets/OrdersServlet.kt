package com.example.servlets

import com.example.consumeBuffer
import com.example.dao.OrderDao
import com.example.models.OrderCreateModel
import com.example.models.OrderModel
import com.google.gson.Gson
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "OrdersServlet", urlPatterns = ["/orders"])
class OrdersServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        val items = OrderDao.allOrders
        val reply = Gson().toJson(items)
        res.addHeader("content-type", "application/json")
        res.writer.write(reply)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        val body = consumeBuffer(req.reader)
        val order = Gson().fromJson(body, OrderCreateModel::class.java)
        val item = OrderDao.addOrder(order)
        val reply = Gson().toJson(item)
        res.addHeader("content-type", "application/json")
        res.writer.write(reply)
    }

    override fun doPut(req: HttpServletRequest, res: HttpServletResponse) {
        val body = consumeBuffer(req.reader)
        val order = Gson().fromJson(body, OrderModel::class.java)
        OrderDao.updateOrder(order)
    }
}