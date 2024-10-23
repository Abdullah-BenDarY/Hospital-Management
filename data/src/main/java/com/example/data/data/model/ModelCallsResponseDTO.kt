package com.example.data.data.model

import android.os.Parcelable
import com.example.domain.models.ModelCallsResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelCallsResponseDTO(
    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String,
) : Parcelable {
    fun toModelCallsResponse(): ModelCallsResponse {
        return ModelCallsResponse(
            status = status,
            message = message,
        )
    }
}