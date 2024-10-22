package com.example.data.data.dataSource.dataSourcesContract

import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import kotlinx.coroutines.flow.Flow

interface HrDataSource {
    fun getAllUsers(type: String): Flow<ApiResult<ModelAllUsers>?>
}