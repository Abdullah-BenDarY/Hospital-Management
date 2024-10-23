package com.example.data.repoisitoriesImpl

import com.example.data.data.dataSource.dataSourcesContract.DoctorDataSource
import com.example.data.data.dataSource.dataSourcesContract.HrDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import com.example.domain.models.ModelCallsResponse
import com.example.domain.models.ModelDoctorCalls
import com.example.domain.repoisitories.DoctorRepo
import com.example.domain.repoisitories.HrRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DoctorRepoImpl @Inject constructor(
    private val doctorDataSource: DoctorDataSource
) : DoctorRepo {
    override fun getAllCalls(): Flow<ApiResult<ModelDoctorCalls>?> =
        doctorDataSource.getAllCAlls()

    override fun acceptRejectCall(id: Int, status: String): Flow<ApiResult<ModelCallsResponse>?> =
        doctorDataSource.acceptRejectCalls(id = id, status = status)

}