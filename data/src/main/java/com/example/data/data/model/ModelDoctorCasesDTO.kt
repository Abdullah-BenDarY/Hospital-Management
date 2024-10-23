package com.example.data.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.models.CasesItem
import com.example.domain.models.ModelDoctorCalls
import com.example.domain.models.ModelDoctorCases
import com.google.gson.annotations.SerializedName

@Parcelize
data class ModelDoctorCasesDTO(

	@field:SerializedName("data")
	val data: List<CasesItemDTO?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable{
	fun toDoctorCases(): ModelDoctorCases {
		return ModelDoctorCases(
			data = data?.map { it?.toData()},
			message = message,
			status = status
		)
	}
}

@Parcelize
data class CasesItemDTO(

	@field:SerializedName("patient_name")
	val patientName: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable{
	fun toData(): CasesItem {
		return CasesItem(
		patientName = patientName,
			createdAt = createdAt,
			id = id
		)
	}
}
