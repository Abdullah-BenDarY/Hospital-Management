package com.example.domain.customExeption

class ServerError (
    serverMessage:String? = "Something went wrong") : Throwable(serverMessage)