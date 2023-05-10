package com.example.backendjakartaee

import java.sql.Connection
import java.sql.DriverManager

object SqlService {
    private var isInstantiated = false
    private lateinit var connection: Connection

    private fun instantiate() {
        if (isInstantiated) {
            return
        }
        Class.forName("org.postgresql.Driver")
        connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/postgres",
            "postgres",
            "changeme"
        )
    }

    fun getConnection(): Connection {
        instantiate()
        return connection
    }
}

