package com.example.data.data.DataSource.dataSourcesImpl

import com.example.data.data.WebServices
import com.example.data.data.DataSource.dataSourcesContract.AuthOnlineDataSource
import com.example.data.data.DataSource.dataSourcesContract.ProfileDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import executeApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// contract implementation for news data source
class ProfileDataSourceImpl @Inject constructor
    (private val apiService: WebServices) : ProfileDataSource {

    override fun getProfile(id : Int): Flow<ApiResult<ModelLogin>?> {
        return executeApi {
            apiService.showProfile(id).toModelLogin()
        }
    }
}