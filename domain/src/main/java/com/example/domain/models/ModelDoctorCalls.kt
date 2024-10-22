package com.example.domain.models

data class ModelDoctorCalls(

    val data: List<DoctorCallsData?>? = null,
    val message: String? = null,
    val status: Int? = null
)

data class DoctorCallsData(
    val patientName: String? = null,
    val createdAt: String? = null,
    val id: Int? = null,
    val status: String? = null
)
