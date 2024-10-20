package com.example.data.data.interceptor
import com.example.data.data.dataSource.dataSourcesContract.AuthOfflineDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AuthInterceptor @Inject constructor(
    private val authOfflineDataSource: AuthOfflineDataSource
) : Interceptor , CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
   private var token: String? = null
    init {
        launch (Dispatchers.IO){
             token = authOfflineDataSource.retrieveUser()?.first()?.data?.accessToken
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        token?.let {
            newBuilder.header("Authorization", it)
        }
        return chain.proceed(newBuilder.build())
    }
}
