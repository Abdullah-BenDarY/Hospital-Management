package com.example.domain.repoisitories

import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import com.example.domain.models.ModelCallsResponse
import com.example.domain.models.ModelDoctorCalls
import com.example.domain.models.ModelDoctorCases
import kotlinx.coroutines.flow.Flow

interface DoctorRepo {
    fun getAllCalls(): Flow<ApiResult<ModelDoctorCalls>?>
    fun getAllCases(): Flow<ApiResult<ModelDoctorCases>?>

    fun acceptRejectCall (id: Int, status: String): Flow<ApiResult<ModelCallsResponse>?>
}