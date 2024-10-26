package com.example.data.model

import android.os.Parcelable
import com.example.domain.models.ModelAllUsers
import com.example.domain.models.UsersData
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelAllUsersDTO(
    @field:SerializedName("data")
    val data: List<UsersDataDTO>? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Int? = null
) : Parcelable {
    fun toModelAllUsers(): ModelAllUsers {
        return ModelAllUsers(
            data = data?.map { it.toData() },
            message = message,
            status = status
        )
    }
}

@Parcelize
data class UsersDataDTO(
    @field:SerializedName("avatar")
    val avatar: String? = null,
    @field:SerializedName("first_name")
    val first_name: String? = null,
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("type")
    val type: String? = null
) : Parcelable {
    fun toData(): UsersData {
        return UsersData(
            id = id,
            avatar = avatar,
            first_name = first_name,
            type = type,
        )
    }
}