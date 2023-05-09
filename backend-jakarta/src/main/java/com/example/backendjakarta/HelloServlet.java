package com.example.backendjakarta;

import java.io.*;
import java.util.List;

import com.example.backendjakarta.dao.MenuItemDao;
import com.example.backendjakarta.models.MenuItemModel;
import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.SneakyThrows;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void init() {

    }

    @SneakyThrows
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<MenuItemModel> items = MenuItemDao.findAll();
        String json = new Gson().toJson(items);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    public void destroy() {
    }
}