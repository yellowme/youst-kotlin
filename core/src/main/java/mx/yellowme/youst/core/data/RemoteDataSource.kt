package mx.yellowme.youst.core.data

import retrofit2.Response
import java.io.IOException

sealed class Result<out Model : Any> {
    data class Success<out Model : Any>(val data: Model) : Result<Model>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result: Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                //Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }

        return data
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T> {
        val response = call.invoke()
        if (response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}