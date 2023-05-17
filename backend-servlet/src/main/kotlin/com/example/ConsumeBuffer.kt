package com.example

import java.io.BufferedReader

fun consumeBuffer(reader: BufferedReader): String {
    val stringBuilder = StringBuilder()
    var line: String?

    try {
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line).append("\n")
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        reader.close()
    }

    return stringBuilder.toString()
}