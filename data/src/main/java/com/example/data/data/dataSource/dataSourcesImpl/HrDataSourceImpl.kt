package com.example.data.data.dataSource.dataSourcesImpl

import com.example.data.data.apiCalls.WebServices
import com.example.data.data.dataSource.dataSourcesContract.HrDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import executeApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HrDataSourceImpl @Inject constructor
    (private val apiService: WebServices) : HrDataSource {

    override fun getAllUsers(type: String): Flow<ApiResult<ModelAllUsers>?> {
        return executeApi {
            apiService.getAllUsers(type).toModelAllUsers()
        }
    }
}