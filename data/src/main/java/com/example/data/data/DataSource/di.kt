package com.example.data.data.DataSource


import com.example.data.data.DataSource.dataSourcesContract.AuthOfflineDataSource
import com.example.data.data.DataSource.dataSourcesContract.AuthOnlineDataSource
import com.example.data.data.DataSource.dataSourcesContract.ProfileDataSource
import com.example.data.data.DataSource.dataSourcesImpl.AuthOfflineDataSourceImpl
import com.example.data.data.DataSource.dataSourcesImpl.AuthOnlineDataSourceImpl
import com.example.data.data.DataSource.dataSourcesImpl.ProfileDataSourceImpl
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


    @Binds
    abstract fun bindProfileDataSource (
        profileDataSourceImpl : ProfileDataSourceImpl
    ): ProfileDataSource
}