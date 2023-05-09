package com.example.backendjakarta.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderContentModel {
    private int orderId;
    private MenuItemModel menuItem;
    private int quantity;
}