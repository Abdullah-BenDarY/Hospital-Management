import com.example.domain.ApiResult
import com.example.domain.ApiResult.*
import com.example.domain.customExeption.ConnectionError
import com.example.domain.customExeption.ServerError
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

fun <T> executeApi(api: suspend () -> T)= flow {
    try {
        val response = Success(api.invoke())
        emit(response)
    } catch (ex: Exception) {
        when (ex) {
            is SocketTimeoutException -> {
                // Handle timeout specifically
            emit(Failure(throw ConnectionError("Request timed out, please try again.")))
            }

            is IOException,
            is TimeoutException -> {
                // Handle network-related issues (no internet, etc.)
                emit(Failure(throw ConnectionError("Network error, please check your internet connection.")))
            }

            else -> {
                // Catch any other unexpected exceptions
                emit(Failure(throw ServerError("An unexpected error occurred: ${ex.localizedMessage}")))
            }
        }
    }
}