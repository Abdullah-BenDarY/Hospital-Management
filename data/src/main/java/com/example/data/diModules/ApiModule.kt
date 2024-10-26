package com.example.data.diModules

import android.util.Log
import com.example.data.BASE_URL
import com.example.data.apiCalls.DoctorCalls
import com.example.data.apiCalls.WebServices
import com.example.data.interceptor.InterceptorsModule
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// di
// hilt
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor():HttpLoggingInterceptor{
        val loggingInterceptor = HttpLoggingInterceptor {
            Log.e("api->", it)
        }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @InterceptorsModule.OkHttpAuthInterceptor authInterceptor: Interceptor

    ):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideGson():Gson{
        return Gson()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,
                        gsonConverterFactory: GsonConverterFactory, ):Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }

    @Provides
    @Singleton
    fun provideDoctorervices(retrofit: Retrofit): DoctorCalls {
        return retrofit.create(DoctorCalls::class.java)
    }
}