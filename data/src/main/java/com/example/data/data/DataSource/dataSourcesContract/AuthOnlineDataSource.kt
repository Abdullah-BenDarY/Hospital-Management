package com.example.data.data.DataSource.dataSourcesContract

import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import kotlinx.coroutines.flow.Flow

// contract between data and domain to pass data from dataLayer to domainLayer
interface AuthOnlineDataSource {
    // contract fun to return a list of news to domain layer
    fun invokeLogin(email : String, password : String)  : Flow<ApiResult<ModelLogin>?>
}