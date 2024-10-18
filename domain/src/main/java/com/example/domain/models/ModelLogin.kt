package com.example.domain.models

data class ModelLogin(

    val data: Data? = null,

    val message: String? = null,

    val status: Int? = null
)


data class Data(

    val birthday: String? = null,

    val address: String? = null,

    val gender: String? = null,

    val mobile: String? = null,

    val verified: Boolean? = null,

    val lastName: String? = null,

    val createdAt: String? = null,

    val type: String? = null,

    val tokenType: String? = null,

    val accessToken: String? = null,

    val specialist: String? = null,

    val id: Int? = null,

    val firstName: String? = null,

    val email: String? = null,

    val status: String? = null
)
