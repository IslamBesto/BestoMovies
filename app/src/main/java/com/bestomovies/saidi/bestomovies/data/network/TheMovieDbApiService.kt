package com.bestomovies.saidi.bestomovies.data.network

import com.bestomovies.saidi.bestomovies.BuildConfig
import com.bestomovies.saidi.bestomovies.data.network.response.TheMovieDbApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://api.themoviedb.org/3/"

interface TheMovieDbApiService {


    //https://api.themoviedb.org/3/movie/top_rated?api_key=c173cfcd8c796433ea8d65e9e9175444&language=en-US&page=1

    @GET("movie/top_rated")
    fun getTopRatedMovies(
            @Query("language") language: String = "en-US"
    ): Deferred<TheMovieDbApi>

    @GET("movie/popular")
    fun getPopularMovies(
            @Query("language") language: String = "en-US"
    ): Deferred<TheMovieDbApi>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
            @Query("language") language: String = "en-US"
    ): Deferred<TheMovieDbApi>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityIntercetor): TheMovieDbApiService {
            val requestInteceptor = Interceptor { chain ->
                val url = chain.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("api_key", BuildConfig.API_KEY)
                        .build()

                val request = chain.request()
                        .newBuilder()
                        .url(url)
                        .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(requestInteceptor)
                    .addInterceptor(connectivityInterceptor)
                    .build()

            return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TheMovieDbApiService::class.java)
        }
    }
}