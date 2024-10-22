package com.example.data.data.apiCalls


import com.example.data.data.model.ModelAllUsersDTO
import com.example.data.data.model.ModelLoginDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WebServices {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): ModelLoginDTO


    @FormUrlEncoded
    @POST("show-profile")
    suspend fun showProfile(
        @Field("user_id") userId: Int
    ): ModelLoginDTO

    @GET("doctors")
    suspend fun getAllUsers(
        @Query("type")
        type: String
    ): ModelAllUsersDTO
}