package com.example.domain.customExeption

class ServerError (
    val statusMsg:String ?= null,
    val serverMessage:String? = "Something went wrong") : Throwable(serverMessage)