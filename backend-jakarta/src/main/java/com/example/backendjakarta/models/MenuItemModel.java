package com.example.backendjakarta.models;

import com.example.backendjakarta.JsonFormattable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemModel implements JsonFormattable {
    private int id;
    private String name;
    private BigDecimal price;
}