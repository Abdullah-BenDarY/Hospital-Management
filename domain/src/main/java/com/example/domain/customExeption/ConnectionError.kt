package com.example.domain.customExeption


class ConnectionError(message: String = "No internet connection please check your internet") :
    Throwable(message) {
}
