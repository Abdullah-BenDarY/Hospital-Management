package com.example.data.data.dataSource.dataSourcesContract

import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import com.example.domain.models.ModelDoctorCalls
import kotlinx.coroutines.flow.Flow

interface DoctorDataSource {
    fun getAllCAlls(): Flow<ApiResult<ModelDoctorCalls>?>
}