package com.example.data.data.onlineDataSource.dataSourcesContract

import com.example.domain.models.ModelLogin
import kotlinx.coroutines.flow.Flow

interface AuthOfflineDataSource {
    fun retrieveUser(): Flow<ModelLogin>?
    suspend fun saveUser(user: ModelLogin)
}