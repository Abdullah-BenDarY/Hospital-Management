package com.example.data.dataSource.dataSourcesImpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.data.dataSource.dataSourcesContract.AuthOfflineDataSource
import com.example.domain.models.ModelLogin
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthOfflineDataSourceImpl @Inject constructor(
    private val userDataStore: DataStore<Preferences>,
    private val provideGson: Gson
) : AuthOfflineDataSource {

    private val response = stringPreferencesKey("loginResponse")

    override fun retrieveUser(): Flow<ModelLogin> {
        return userDataStore.data
            .map { preferences ->
                val json = preferences[response]
                json?.let {
                    provideGson.fromJson(it, ModelLogin::class.java)
                } ?: ModelLogin()
            }
    }

    override suspend fun saveUser(user: ModelLogin) {
        userDataStore.edit { settings ->
            settings[response] = provideGson.toJson(user)
        }
    }
}