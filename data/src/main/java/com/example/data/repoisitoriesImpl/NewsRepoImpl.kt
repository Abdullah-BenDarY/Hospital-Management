package com.example.data.repoisitoriesImpl

import com.example.data.data.onlineDataSource.dataSourcesContract.NewsDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import com.example.domain.repoisitories.NewsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(private val newsDataSource: NewsDataSource) : NewsRepo {

    override fun invokeLogin(email: String, password: String): Flow<ApiResult<ModelLogin>?> {
        return newsDataSource.invokeLogin(email , password)
    }
}