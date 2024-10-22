package com.example.domain.models

data class ModelAllUsers(
    val `data`: List<UsersData>?,
    val message: String? = null,
    val status: Int? = null
)

data class UsersData(
    val avatar: String? = null,
    val first_name: String? = null,
    val id: Int? = null,
    val type: String? = null
)