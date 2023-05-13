package com.example.backendjakartaee

import java.sql.Connection
import java.sql.DriverManager
import io.github.cdimascio.dotenv.dotenv

object SqlService {
    private var isInstantiated = false
    private lateinit var connection: Connection

    private fun instantiate() {
        if (isInstantiated) {
            return
        }
        Class.forName("org.postgresql.Driver")

        val env = dotenv()

        connection = DriverManager.getConnection(
            env["DB_URL"],
            env["DB_USERNAME"],
            env["DB_PASSWORD"]
        )
    }

    fun getConnection(): Connection {
        instantiate()
        return connection
    }
}

