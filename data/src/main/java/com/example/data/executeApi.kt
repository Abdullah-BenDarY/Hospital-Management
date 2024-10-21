import com.example.domain.ApiResult
import com.example.domain.ApiResult.Failure
import com.example.domain.ApiResult.Success
import com.example.domain.customExeption.ConnectionError
import com.example.domain.customExeption.ServerError
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

fun <T> executeApi(api: suspend () -> T) = flow<ApiResult<T>>{
    emit(Success(api.invoke()))

} .catch { ex ->
    when (ex) {
        is SocketTimeoutException -> {
            emit(Failure(ConnectionError("Request timed out, please try again."))) }

        is IOException, is TimeoutException ->
            emit(Failure(ConnectionError("Network error, please check your internet connection.")))

        is HttpException -> emit(Failure(ServerError(ex.localizedMessage)))

        else -> emit(Failure(ServerError("An unexpected error occurred: ${ex.localizedMessage}")))
    }
}