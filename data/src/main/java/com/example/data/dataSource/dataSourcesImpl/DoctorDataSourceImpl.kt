package com.example.data.dataSource.dataSourcesImpl
import com.example.data.apiCalls.DoctorCalls
import com.example.data.dataSource.dataSourcesContract.DoctorDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelCallsResponse
import com.example.domain.models.ModelCaseDetails
import com.example.domain.models.ModelDoctorCalls
import com.example.domain.models.ModelDoctorCases
import executeApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DoctorDataSourceImpl @Inject constructor
    (private val apiService: DoctorCalls) : DoctorDataSource {

    override fun getAllCalls(): Flow<ApiResult<ModelDoctorCalls>?> =
    executeApi {
            apiService.getDoctorCalls().toModelDoctorCalls()
    }

    override fun acceptRejectCalls(id: Int, status: String): Flow<ApiResult<ModelCallsResponse>?> =
         executeApi {
            apiService.acceptOrRejectCall(id = id, status = status).toModelCallsResponse()
        }

    override fun getAllCases(): Flow<ApiResult<ModelDoctorCases>?> =
        executeApi {
            apiService.getAllCases().toDoctorCases()
        }

    override fun getCaseDetails(id: Int): Flow<ApiResult<ModelCaseDetails>?> =
        executeApi {
            apiService.caseDetails(id = id).toCaseStatus()
        }

    }
