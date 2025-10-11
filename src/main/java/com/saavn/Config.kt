package com.saavn

import io.github.cdimascio.dotenv.Dotenv

class Config {
    companion object {
        // Make a .env file to load all the configs
        val dotenv = Dotenv.load()

        fun get(key: String) : String? {
            return dotenv.get(key.uppercase())
        }
        // Make a config verifier 
    }
}
