package com.example.servlets

import com.example.consumeBuffer
import com.example.dao.OrderContentDao
import com.example.dao.OrderDao
import com.example.models.OrderContentCreateModel
import com.example.models.OrderCreateModel
import com.example.models.OrderModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "OrdersContentsServlet", urlPatterns = ["/orders/*"])
class OrdersContentsServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        val orderId = req.pathInfo.substring(1, req.pathInfo.lastIndexOf('/')).toInt()

        val items = OrderContentDao.findManyByOrder(orderId)

        val reply = Gson().toJson(items)
        res.addHeader("content-type", "application/json")
        res.writer.write(reply)
    }

    override fun doDelete(req: HttpServletRequest, resp: HttpServletResponse) {
        val orderId = req.pathInfo.substring(1, req.pathInfo.lastIndexOf('/')).toInt()

        OrderContentDao.deleteByOrder(orderId)
        OrderDao.deleteOrder(orderId)
    }

    override fun doPut(req: HttpServletRequest, res: HttpServletResponse) {
        val orderId = req.pathInfo.substring(1, req.pathInfo.lastIndexOf('/')).toInt()
        val body = consumeBuffer(req.reader)
        val listType = object : TypeToken<List<OrderContentCreateModel>>() {}.type
        val contents = Gson().fromJson<List<OrderContentCreateModel>>(body, listType)

        OrderContentDao.deleteByOrder(orderId)
        contents.forEach {
            OrderContentDao.create(orderId, it)
        }
    }
}