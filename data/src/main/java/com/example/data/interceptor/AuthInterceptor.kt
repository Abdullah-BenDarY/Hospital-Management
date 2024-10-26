package com.example.data.interceptor

import com.example.data.AUTHORIZATION
import com.example.data.dataSource.dataSourcesContract.AuthOfflineDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AuthInterceptor @Inject constructor(
    private val authOfflineDataSource: AuthOfflineDataSource,
    override val coroutineContext: CoroutineContext = Dispatchers.IO
) : Interceptor, CoroutineScope {

    override fun intercept(chain: Interceptor.Chain): Response {
        return runBlocking {
            val token = retrieveToken()
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
            if (token != null) {
                requestBuilder.addHeader(AUTHORIZATION, "Bearer $token")}
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    private var cachedToken: String? = null

    private suspend fun retrieveToken(): String? {
        return cachedToken ?: withContext(Dispatchers.IO) {
            authOfflineDataSource.retrieveUser()?.first()?.data?.accessToken.also {
                cachedToken = it
            }
        }
    }
}
