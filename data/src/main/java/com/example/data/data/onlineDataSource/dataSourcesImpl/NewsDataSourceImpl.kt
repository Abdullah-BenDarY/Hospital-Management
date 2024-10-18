package com.example.data.data.onlineDataSource.dataSourcesImpl

import com.example.data.data.WebServices
import com.example.data.data.onlineDataSource.dataSourcesContract.NewsDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import executeApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// contract implementation for news data source
class NewsDataSourceImpl @Inject constructor
    (private val apiService: WebServices) : NewsDataSource {

    override fun invokeLogin(email: String, password: String): Flow<ApiResult<ModelLogin>?> {
        return executeApi {
            apiService.login(email = email, password = password).toModelLogin()
        }
    }
}