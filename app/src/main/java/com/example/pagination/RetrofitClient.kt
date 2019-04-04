package com.example.pagination

import com.kotlab.supreme.paging.response.ApiResponse
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private var retrofit: Retrofit? = null

    fun sampleTest(url: String): Retrofit {

        //logging interceptor
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        //okhttp library
        val client = OkHttpClient.Builder()
        client.connectTimeout(30, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
        client.writeTimeout(30, TimeUnit.SECONDS)
        //client.cache(cache)
        client.build()

        //want to avoid custom url encoding , here is solution
        client.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                var string = request.url().toString()
                string = string.replace("%2C", ",")
                //string = string.replace("%3D", "=");

                val newRequest = Request.Builder()
                    .url(string)
                    .build()

                return chain.proceed(newRequest)
            }
        })

        if (BuildConfig.DEBUG) {
            client.addInterceptor(logging)
        }

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
        }
        return retrofit!!
    }

    interface Api {

        /**
         * feed top head line in endless manner
         */
        @GET("top-headlines")
        fun getTopHeadlinesEndless(
            @Query("page") page: Int,
            @Query("pageSize") pageSize: Int,
            @Query("country") country: String,
            @Query("apiKey") apiKey: String
        ): Call<ApiResponse>

        /**
         * feed top head line with lazy loading
         */
        @GET("top-headlines")
        fun getTopHeadlinesLazy(
            @Query("country") country: String,
            @Query("apiKey") apiKey: String
        ): Call<ResponseBody>

        /**
         * weather information api using query
         */
        @GET("weather")
        fun getWeather(
            @Query("q") cityNameAndCountryCode: String,
            @Query("appid") appid: String
        ): Call<ResponseBody>

        /**
         * weather information api using query
         */
        @GET("weather")
        fun getWeatherLatLon(
            @Query("lat") lat: String,
            @Query("lon") lon: String,
            @Query("appid") appid: String
        ): Call<ResponseBody>

    }
}