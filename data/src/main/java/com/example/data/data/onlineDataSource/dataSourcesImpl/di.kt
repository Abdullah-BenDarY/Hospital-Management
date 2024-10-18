package com.example.data.data.onlineDataSource.dataSourcesImpl

import com.example.data.data.onlineDataSource.dataSourcesContract.NewsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceBind(){

    // to inject the news data source interface
    @Binds
    abstract fun bindDataSource (
        dataSourceImpl : NewsDataSourceImpl
    ): NewsDataSource

}