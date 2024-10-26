package com.example.domain.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ModelCaseDetails(

	val data: CaseData? = null,

	val message: String? = null,

	val status: Int? = null
) : Parcelable

@Parcelize
data class CaseData(

	val image: String? = null,

	val respiratoryRate: String? = null,

	val createdAt: String? = null,

	val description: String? = null,

	val heartRate: String? = null,

	val bloodPressure: String? = null,

	val sugarAnalysis: String? = null,

	val docId: Int? = null,

	val analysisId: String? = null,

	val measurementNote: String? = null,

	val doctorId: String? = null,

	val medicalRecordNote: String? = null,

	val tempreture: String? = null,

	val phone: String? = null,

	val caseStatus: String? = null,

	val patientName: String? = null,

	val fluidBalance: String? = null,

	val id: Int? = null,

	val nurseId: String? = null,

	val age: String? = null,

	val status: String? = null
) : Parcelable
