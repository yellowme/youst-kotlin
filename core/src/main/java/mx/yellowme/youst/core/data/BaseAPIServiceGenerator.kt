package mx.yellowme.youst.core.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provides methods to easily create API services providing the concrete service class and
 * the user auth token if is needed.
 */
open class BaseAPIServiceGenerator(baseURL: String) {

    private var httpClient: OkHttpClient.Builder? = null

    private val builder = Retrofit.Builder().baseUrl(baseURL)

    /**
     * Generates a out of the box retrofit service with a session id on the headers.
     *
     * @param serviceClass service defined using the API reference.
     * @param <S> type of service to call.
     * @return out of the box retrofit service to be used to make requests.
    </S> */
    fun <S> createService(serviceClass: Class<S>): S {
        httpClient ?: run {
            httpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
        }

        val client = httpClient!!.build()
        builder.addConverterFactory(MoshiConverterFactory.create())

        val retrofit = builder
            .client(client)
            .build()

        return retrofit.create(serviceClass)
    }

}
