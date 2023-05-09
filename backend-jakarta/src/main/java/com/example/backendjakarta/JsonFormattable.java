package com.example.backendjakarta;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface JsonFormattable {
    default String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}