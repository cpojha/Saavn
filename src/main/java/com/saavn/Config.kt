package com.saavn

import io.github.cdimascio.dotenv.Dotenv

class Config {
    companion object {
        val dotenv = Dotenv.load()

        fun get(key: String) : String? {
            return dotenv.get(key.uppercase())
        }
    }
}