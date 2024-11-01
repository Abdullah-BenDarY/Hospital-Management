package com.example.data.apiCalls

import com.example.data.NURSE
import com.example.data.model.ModelAllUsersDTO
import com.example.data.model.ModelCallsResponseDTO
import com.example.data.model.ModelCaseDetailsDTO
import com.example.data.model.ModelDoctorCallsDTO
import com.example.data.model.ModelDoctorCasesDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("doctors")
    suspend fun getNurseList(
        @Query("type")
        type : String = NURSE
    ): ModelAllUsersDTO

    @FormUrlEncoded
    @POST("add-nurse")
    suspend fun addNurse(
        @Field("call_id") callId: Int ?= null,
        @Field("user_id") userId: Int ?= null
    ):ModelCallsResponseDTO

    @FormUrlEncoded
    @POST("make-request")
    suspend fun makeRequest(
        @Field("call_id") callId: Int,
        @Field("user_id") userId: Int,
        @Field("note") note: String?,
        @Field("types[0]") type0: String,
        @Field("types[1]") type1: String?,
        @Field("types[2]") type2: String?,
    ): ModelCallsResponseDTO

}