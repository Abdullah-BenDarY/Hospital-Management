package com.example.data.repoisitoriesImpl

import com.example.domain.repoisitories.AuthRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Repostories() {

    // to inject the news repo interface
    @Binds
    abstract fun provideNewsRepo(
        authRepoRepoImpl: AuthRepoRepoImpl
    ): AuthRepo
}
