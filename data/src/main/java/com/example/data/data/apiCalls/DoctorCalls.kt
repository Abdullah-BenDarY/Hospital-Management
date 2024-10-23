package com.example.data.data.apiCalls

import com.example.data.data.model.ModelCallsResponseDTO
import com.example.data.data.model.ModelDoctorCallsDTO
import com.example.data.data.model.ModelDoctorCasesDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface DoctorCalls {

    @GET("calls")
    suspend fun getDoctorCalls(
    ): ModelDoctorCallsDTO

    @FormUrlEncoded
    @PUT("calls-accept/{id}")
    suspend fun acceptOrRejectCall(
        @Path("id") id: Int,
        @Field("status") status: String
    ): ModelCallsResponseDTO

    @GET("case")
    suspend fun getAllCases():ModelDoctorCasesDTO
}