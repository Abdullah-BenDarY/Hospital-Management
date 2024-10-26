package com.example.data.dataSource


import com.example.data.dataSource.dataSourcesContract.AuthOfflineDataSource
import com.example.data.dataSource.dataSourcesContract.AuthOnlineDataSource
import com.example.data.dataSource.dataSourcesContract.DoctorDataSource
import com.example.data.dataSource.dataSourcesContract.HrDataSource
import com.example.data.dataSource.dataSourcesContract.ProfileDataSource
import com.example.data.dataSource.dataSourcesImpl.AuthOfflineDataSourceImpl
import com.example.data.dataSource.dataSourcesImpl.AuthOnlineDataSourceImpl
import com.example.data.dataSource.dataSourcesImpl.DoctorDataSourceImpl
import com.example.data.dataSource.dataSourcesImpl.HrDataSourceImpl
import com.example.data.dataSource.dataSourcesImpl.ProfileDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceBind() {

    @Binds
    abstract fun bindOnlineDataSource(
        onlineDataSourceImpl: AuthOnlineDataSourceImpl
    ): AuthOnlineDataSource

    @Binds
    abstract fun bindOfflineDataSource(
        offlineDataSourceImpl: AuthOfflineDataSourceImpl
    ): AuthOfflineDataSource


    @Binds
    abstract fun bindProfileDataSource(
        profileDataSourceImpl: ProfileDataSourceImpl
    ): ProfileDataSource

    @Binds
    abstract fun bindHrDataSource(
        hrDataSourceImpl: HrDataSourceImpl
    ): HrDataSource

    @Binds
    abstract fun bindDoctorDataSource(
        doctorDataSourceImpl: DoctorDataSourceImpl
    ): DoctorDataSource
}