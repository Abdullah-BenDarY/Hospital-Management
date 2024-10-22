package com.example.data.repoisitoriesImpl

import com.example.data.data.dataSource.dataSourcesContract.HrDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import com.example.domain.repoisitories.HrRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HrRepoImpl @Inject constructor(
    private val hrDataSource: HrDataSource
) : HrRepo {
    override fun getAllUsers(type: String): Flow<ApiResult<ModelAllUsers>?> =
        hrDataSource.getAllUsers(type)

}