package com.example.domain.models

data class ModelDoctorCases(

	val data: List<CasesItem?>? = null,

	val message: String? = null,

	val status: Int? = null
)
data class CasesItem(

	val patientName: String? = null,

	val createdAt: String? = null,

	val id: Int? = null
)