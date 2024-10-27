package com.example.data.apiCalls

import com.example.data.model.ModelCallsResponseDTO
import com.example.data.model.ModelCaseDetailsDTO
import com.example.data.model.ModelDoctorCallsDTO
import com.example.data.model.ModelDoctorCasesDTO
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
    suspend fun getAllCases(): ModelDoctorCasesDTO

    @GET("case/{id}")
    suspend fun caseDetails(
        @Path("id") id: Int
    ): ModelCaseDetailsDTO

    @PUT("calls/{id}")
    suspend fun endCase(
        @Path("id") id: Int
    ): ModelCallsResponseDTO

}