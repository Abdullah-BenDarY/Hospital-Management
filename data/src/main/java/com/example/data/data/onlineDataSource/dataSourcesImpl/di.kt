package com.example.data.data.onlineDataSource.dataSourcesImpl


import com.example.data.data.onlineDataSource.dataSourcesContract.AuthOfflineDataSource
import com.example.data.data.onlineDataSource.dataSourcesContract.AuthOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceBind(){

    @Binds
    abstract fun bindOnlineDataSource (
        onlineDataSourceImpl : AuthOnlineDataSourceImpl
    ): AuthOnlineDataSource

    @Binds
    abstract fun bindOfflineDataSource (
        offlineDataSourceImpl : AuthOfflineDataSourceImpl
    ): AuthOfflineDataSource
}