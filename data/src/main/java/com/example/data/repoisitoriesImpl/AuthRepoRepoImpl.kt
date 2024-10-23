package com.example.data.repoisitoriesImpl

import com.example.data.data.dataSource.dataSourcesContract.AuthOfflineDataSource
import com.example.data.data.dataSource.dataSourcesContract.AuthOnlineDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import com.example.domain.repoisitories.AuthRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepoRepoImpl @Inject constructor(
    private val authOnlineDataSource: AuthOnlineDataSource,
    private val authOfflineDataSource: AuthOfflineDataSource
) : AuthRepo {

    override fun invokeLogin(email: String?, password: String?): Flow<ApiResult<ModelLogin>> =
        flow {

            if (email == null || password == null) {
                val cashedUser = authOfflineDataSource.retrieveUser()?.first()
                if (cashedUser != null) emit(ApiResult.Success(cashedUser))
                return@flow
            }
            authOnlineDataSource.invokeLogin(email, password).collect { result ->
                if (result is ApiResult.Success) result.data.let {
                    authOfflineDataSource.saveUser(it)
                }
                emit(result!!)
            }
        }
}