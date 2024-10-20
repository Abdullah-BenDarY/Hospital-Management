package com.example.domain.repoisitories

import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import kotlinx.coroutines.flow.Flow

interface ProfileRepo {
     fun getProfile(id : Int): Flow<ApiResult<ModelLogin>?>
}