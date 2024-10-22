package com.example.data.data.model

import android.os.Parcelable
import com.example.domain.models.DoctorCallsData
import com.example.domain.models.ModelDoctorCalls
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelDoctorCallsDTO(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
): Parcelable{
	fun toModelDoctorCalls(): ModelDoctorCalls {
		return ModelDoctorCalls(
			data = data?.map { it?.toData()},
			message = message,
			status = status
		)
	}
}

@Parcelize
data class DataItem(

	@field:SerializedName("patient_name")
	val patientName: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
):Parcelable{
	fun toData(): DoctorCallsData {
	return DoctorCallsData(
	patientName= patientName,
		createdAt= createdAt,
		id= id,
		status= status
	)
}
}
