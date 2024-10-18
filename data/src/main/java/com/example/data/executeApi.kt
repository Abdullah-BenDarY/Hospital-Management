import com.example.domain.ApiResult
import com.example.domain.ApiResult.Failure
import com.example.domain.ApiResult.Success
import com.example.domain.customExeption.ConnectionError
import com.example.domain.customExeption.ServerError
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

fun <T> executeApi(api: suspend () -> T) = flow<ApiResult<T>> {
    try {
        emit(Success(api.invoke()))

    } catch (ex: Exception) {
        when (ex) {
            is SocketTimeoutException -> {
                // Handle timeout specifically
                emit(Failure(ConnectionError("Request timed out, please try again.")))
            }

            is IOException,
            is TimeoutException -> {
                // Handle network-related issues (no internet, etc.)
                emit(Failure(ConnectionError("Network error, please check your internet connection.")))
            }

            else -> {
                // Handle any other unexpected exceptions
                emit(Failure(ServerError("An unexpected error occurred: ${ex.localizedMessage}")))
            }
        }
    }
}
