package com.example.data.dataSource.dataSourcesContract

import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import com.example.domain.models.ModelCallsResponse
import com.example.domain.models.ModelCaseDetails
import com.example.domain.models.ModelDoctorCalls
import com.example.domain.models.ModelDoctorCases
import kotlinx.coroutines.flow.Flow

interface DoctorDataSource {
    fun getAllCalls(): Flow<ApiResult<ModelDoctorCalls>?>
    fun getAllCases(): Flow<ApiResult<ModelDoctorCases>?>
    fun getCaseDetails(id: Int): Flow<ApiResult<ModelCaseDetails>?>
    fun acceptRejectCalls(id: Int, status: String): Flow<ApiResult<ModelCallsResponse>?>
    fun invokeEndCase(id: Int): Flow<ApiResult<ModelCallsResponse>?>
    fun getNurseList(): Flow<ApiResult<ModelAllUsers>?>
    fun setNurse(callId: Int, userId: Int): Flow<ApiResult<ModelCallsResponse>?>
}