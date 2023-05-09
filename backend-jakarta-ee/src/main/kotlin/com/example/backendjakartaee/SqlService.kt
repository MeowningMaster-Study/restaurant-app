package com.example.backendjakartaee

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object SqlService {
    private var isInstantiated = false
    private lateinit var connection: Connection
    private fun instantiate() {
        if (!isInstantiated) {
            return
        }
        try {
            Class.forName("org.postgresql.Driver")
        } catch (e: ClassNotFoundException) {
            throw RuntimeException(e)
        }
        try {
            connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "changeme"
            )
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun getConnection(): Connection {
        instantiate()
        return connection
    }
}

