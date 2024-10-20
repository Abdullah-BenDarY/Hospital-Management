package com.example.data.data.DataSource.dataSourcesImpl

import com.example.data.data.WebServices
import com.example.data.data.DataSource.dataSourcesContract.AuthOnlineDataSource
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