package com.example.data.data.dataSource.dataSourcesContract

import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import kotlinx.coroutines.flow.Flow

interface AuthOnlineDataSource {
    fun invokeLogin(email : String, password : String)  : Flow<ApiResult<ModelLogin>?>
}