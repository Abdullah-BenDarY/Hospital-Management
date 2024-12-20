package com.example.data.dataSource.dataSourcesImpl

import com.example.data.apiCalls.WebServices
import com.example.data.dataSource.dataSourcesContract.ProfileDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import executeApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor
    (private val apiService: WebServices) : ProfileDataSource {

    override fun getProfile(id : Int): Flow<ApiResult<ModelLogin>?> {
        return executeApi {
            apiService.showProfile(id).toModelLogin()
        }
    }
}