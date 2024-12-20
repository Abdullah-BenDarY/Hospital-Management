package com.example.domain.repoisitories

import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import com.example.domain.models.ModelLogin
import kotlinx.coroutines.flow.Flow

interface HrRepo {
    fun getAllUsers(type: String): Flow<ApiResult<ModelAllUsers>?>
    fun createUser(
        email: String, password: String, fName: String, lName: String,
        gender: String, specialist: String, birthday: String, status: String,
        address: String, mobile: String, type: String
    ): Flow<ApiResult<ModelLogin>?>
}