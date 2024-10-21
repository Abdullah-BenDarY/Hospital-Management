package com.example.data.data.dataSource


import com.example.data.data.dataSource.dataSourcesContract.AuthOfflineDataSource
import com.example.data.data.dataSource.dataSourcesContract.AuthOnlineDataSource
import com.example.data.data.dataSource.dataSourcesContract.ProfileDataSource
import com.example.data.data.dataSource.dataSourcesImpl.AuthOfflineDataSourceImpl
import com.example.data.data.dataSource.dataSourcesImpl.AuthOnlineDataSourceImpl
import com.example.data.data.dataSource.dataSourcesImpl.ProfileDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
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