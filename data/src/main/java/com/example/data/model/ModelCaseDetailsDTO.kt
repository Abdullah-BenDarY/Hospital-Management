package com.example.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.models.CaseData
import com.example.domain.models.ModelCaseDetails
import com.google.gson.annotations.SerializedName

@Parcelize
data class ModelCaseDetailsDTO(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable{
	fun toCaseStatus(): ModelCaseDetails {
		return ModelCaseDetails(
			data?.toCaseDetails(),
			 message,
			 status

		)
	}
}

@Parcelize
data class Data(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("respiratory_rate")
	val respiratoryRate: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("heart_rate")
	val heartRate: String? = null,

	@field:SerializedName("blood_pressure")
	val bloodPressure: String? = null,

	@field:SerializedName("sugar_analysis")
	val sugarAnalysis: String? = null,

	@field:SerializedName("doc_id")
	val docId: Int? = null,

	@field:SerializedName("analysis_id")
	val analysisId: String? = null,

	@field:SerializedName("measurement_note")
	val measurementNote: String? = null,

	@field:SerializedName("doctor_id")
	val doctorId: String? = null,

	@field:SerializedName("medical_record_note")
	val medicalRecordNote: String? = null,

	@field:SerializedName("tempreture")
	val tempreture: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("case_status")
	val caseStatus: String? = null,

	@field:SerializedName("patient_name")
	val patientName: String? = null,

	@field:SerializedName("fluid_balance")
	val fluidBalance: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("nurse_id")
	val nurseId: String? = null,

	@field:SerializedName("age")
	val age: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable{
	fun toCaseDetails(): CaseData {
		return CaseData(
			image,
			respiratoryRate,
			createdAt,
			description,
			heartRate,
			bloodPressure,
			sugarAnalysis,
			docId,
			analysisId,
			measurementNote,
			doctorId,
			medicalRecordNote,
			tempreture,
			phone,
			caseStatus,
			patientName,
			fluidBalance,
			id,
			nurseId,
			age,
			status

		)
	}
}
