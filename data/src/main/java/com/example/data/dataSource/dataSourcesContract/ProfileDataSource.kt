package com.example.data.dataSource.dataSourcesContract

import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import kotlinx.coroutines.flow.Flow

interface ProfileDataSource {
    fun getProfile(id : Int)  : Flow<ApiResult<ModelLogin>?>
}