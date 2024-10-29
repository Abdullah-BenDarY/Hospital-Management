package com.example.data.repoisitoriesImpl

import com.example.data.dataSource.dataSourcesContract.DoctorDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import com.example.domain.models.ModelCallsResponse
import com.example.domain.models.ModelCaseDetails
import com.example.domain.models.ModelDoctorCalls
import com.example.domain.models.ModelDoctorCases
import com.example.domain.repoisitories.DoctorRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DoctorRepoImpl @Inject constructor(
    private val doctorDataSource: DoctorDataSource
) : DoctorRepo {
    override fun getAllCalls(): Flow<ApiResult<ModelDoctorCalls>?> = doctorDataSource.getAllCalls()

    override fun getAllCases(): Flow<ApiResult<ModelDoctorCases>?> = doctorDataSource.getAllCases()

    override fun getCaseDetails(id: Int): Flow<ApiResult<ModelCaseDetails>?> =
        doctorDataSource.getCaseDetails(id)

    override fun acceptRejectCall(id: Int, status: String): Flow<ApiResult<ModelCallsResponse>?> =
        doctorDataSource.acceptRejectCalls(id = id, status = status)

    override fun endCase(id: Int): Flow<ApiResult<ModelCallsResponse>?> = doctorDataSource.invokeEndCase(id)

    override fun getNurseList(): Flow<ApiResult<ModelAllUsers>?> =doctorDataSource.getNurseList()

    override fun setNurse(callId: Int, userId: Int): Flow<ApiResult<ModelCallsResponse>?> =
        doctorDataSource.setNurse(callId = callId, userId = userId)
}