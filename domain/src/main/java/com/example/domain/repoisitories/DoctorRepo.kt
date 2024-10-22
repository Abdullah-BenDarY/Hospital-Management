package com.example.domain.repoisitories

import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import com.example.domain.models.ModelDoctorCalls
import kotlinx.coroutines.flow.Flow

interface DoctorRepo {
    fun getAllCalls(): Flow<ApiResult<ModelDoctorCalls>?>
}