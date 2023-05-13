package com.example.backendspring.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.example.backendspring.services.MenuItemService
import com.example.backendspring.models.MenuItemModel
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/menu-items")
class MenuItemsController(private val menuItemsService: MenuItemService) {

    @GetMapping
    fun get(): List<MenuItemModel> {
        return menuItemsService.findAll()
    }
}
