package com.example.data.repoisitoriesImpl

import com.example.data.data.dataSource.dataSourcesContract.ProfileDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import com.example.domain.repoisitories.ProfileRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepoImpl @Inject constructor(
    private val profileOfflineDataSource: ProfileDataSource
) : ProfileRepo {

    override fun getProfile(id: Int): Flow<ApiResult<ModelLogin>?> =
        profileOfflineDataSource.getProfile(id)
    }