package com.example.domain.repoisitories

import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import kotlinx.coroutines.flow.Flow

interface HrRepo {
    fun getAllUsers(type : String): Flow<ApiResult<ModelAllUsers>?>
}