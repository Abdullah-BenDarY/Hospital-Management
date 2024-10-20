package com.example.data.data.onlineDataSource.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.data.onlineDataSource.dataSourcesContract.AuthOfflineDataSource
import com.example.data.data.onlineDataSource.dataSourcesContract.AuthOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.userPreferences: DataStore<Preferences> by preferencesDataStore(name = "User-Preferences")

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule{

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.userPreferences
    }

}