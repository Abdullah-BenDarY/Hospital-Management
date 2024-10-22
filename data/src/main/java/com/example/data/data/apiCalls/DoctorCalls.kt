package com.example.data.data.apiCalls

import com.example.data.data.model.ModelDoctorCallsDTO
import retrofit2.http.GET

interface DoctorCalls {

    @GET("calls")
    suspend fun getDoctorCalls(
    ): ModelDoctorCallsDTO
}