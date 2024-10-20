package com.example.domain.repoisitories

import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import kotlinx.coroutines.flow.Flow

interface AuthRepo {
     fun invokeLogin(email: String? = null, password: String? = null): Flow<ApiResult<ModelLogin>>
}