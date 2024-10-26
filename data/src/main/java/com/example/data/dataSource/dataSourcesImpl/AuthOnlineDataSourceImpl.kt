package com.example.data.dataSource.dataSourcesImpl

import com.example.data.apiCalls.WebServices
import com.example.data.dataSource.dataSourcesContract.AuthOnlineDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import executeApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// contract implementation for news data source
class AuthOnlineDataSourceImpl @Inject constructor
    (private val apiService: WebServices) : AuthOnlineDataSource {

    override fun invokeLogin(email: String, password: String): Flow<ApiResult<ModelLogin>?> {
        return executeApi {
            apiService.login(email = email, password = password).toModelLogin()
        }
    }
}