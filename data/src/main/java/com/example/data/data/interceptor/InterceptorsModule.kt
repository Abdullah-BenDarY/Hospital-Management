package com.example.data.data.interceptor

import com.example.data.utils.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterceptorsModule {

    @Provides
    @Singleton
    @OkHttpAuthInterceptor
    fun provideAuthInterceptor(sharedPrefs: SharedPrefs): Interceptor {
        return AuthInterceptor(sharedPrefs)
    }


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OkHttpAuthInterceptor
}
