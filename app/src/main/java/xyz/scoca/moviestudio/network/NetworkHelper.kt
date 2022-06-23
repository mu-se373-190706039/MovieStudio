package xyz.scoca.moviestudio.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.scoca.moviestudio.BuildConfig
import xyz.scoca.moviestudio.network.service.MovieService
import xyz.scoca.moviestudio.utils.Const
import java.util.concurrent.TimeUnit

class NetworkHelper {
    var movieService : MovieService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieService = retrofit.create(MovieService::class.java)
    }

    private fun getClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.writeTimeout(60, TimeUnit.SECONDS)
        httpClient.addInterceptor(createHttpLoggingInterceptor(BuildConfig.DEBUG))
        return httpClient.build()
    }

    private fun createHttpLoggingInterceptor(debugMode: Boolean) : HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if(debugMode) httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        return httpLoggingInterceptor
    }
}