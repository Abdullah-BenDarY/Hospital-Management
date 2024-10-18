package com.example.data.data.model

import android.os.Parcelable
import com.example.domain.models.Data
import com.example.domain.models.ModelLogin
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Parcelize
data class ModelLoginDTO(

    @field:SerializedName("data")
    val data: DataDTO? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Int? = null
): Parcelable {
    fun toModelLogin(): ModelLogin {
        return ModelLogin(
            data = data?.toData(),
            message = message,
            status = status
        )?: ModelLogin()
    }
}

@Parcelize
data class DataDTO(

    @field:SerializedName("birthday")
    val birthday: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("mobile")
    val mobile: String? = null,

    @field:SerializedName("verified")
    val verified: Boolean? = null,

    @field:SerializedName("last_name")
    val lastName: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("token_type")
    val tokenType: String? = null,

    @field:SerializedName("access_token")
    val accessToken: String? = null,

    @field:SerializedName("specialist")
    val specialist: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("first_name")
    val firstName: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable {
    fun toData(): Data {
        return Data(
            birthday = birthday,
            address = address,
            gender = gender,
            mobile = mobile,
            verified = verified,
            lastName = lastName,
            createdAt = createdAt,
            type = type,
            tokenType = tokenType,
            accessToken = accessToken,
            specialist = specialist,
            id = id,
            firstName = firstName,
            email = email,
            status = status
        )
    }
}

