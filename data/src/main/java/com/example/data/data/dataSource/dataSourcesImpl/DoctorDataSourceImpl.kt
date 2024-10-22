package com.example.data.data.dataSource.dataSourcesImpl

import com.example.data.data.apiCalls.DoctorCalls
import com.example.data.data.apiCalls.WebServices
import com.example.data.data.dataSource.dataSourcesContract.DoctorDataSource
import com.example.data.data.dataSource.dataSourcesContract.HrDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import com.example.domain.models.ModelDoctorCalls
import executeApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DoctorDataSourceImpl @Inject constructor
    (private val apiService: DoctorCalls) : DoctorDataSource {



    override fun getAllCAlls(): Flow<ApiResult<ModelDoctorCalls>?> {
        return executeApi {
            apiService.getDoctorCalls().toModelDoctorCalls()
        }
    }
}