package com.example.data.repoisitoriesImpl

import com.example.domain.repoisitories.AuthRepo
import com.example.domain.repoisitories.HrRepo
import com.example.domain.repoisitories.ProfileRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Repostories() {

    // to inject the news repo interface
    @Binds
    abstract fun provideAuthRepo(
        authRepoRepoImpl: AuthRepoRepoImpl
    ): AuthRepo

    @Binds
    abstract fun provideProfileRepo(
        profileRepoImpl: ProfileRepoImpl
    ): ProfileRepo

    @Binds
    abstract fun provideHrRepo(
        hrRepoImpl: HrRepoImpl
    ): HrRepo
}
