package com.example.data.repoisitoriesImpl

import com.example.data.data.DataSource.dataSourcesContract.AuthOfflineDataSource
import com.example.data.data.DataSource.dataSourcesContract.AuthOnlineDataSource
import com.example.data.data.DataSource.dataSourcesContract.ProfileDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import com.example.domain.repoisitories.AuthRepo
import com.example.domain.repoisitories.ProfileRepo
import executeApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepoRepoImpl @Inject constructor(
    private val profileOfflineDataSource: ProfileDataSource
) : ProfileRepo {

    override fun getProfile(id: Int): Flow<ApiResult<ModelLogin>?> {
        return profileOfflineDataSource.getProfile(id)

        }
    }