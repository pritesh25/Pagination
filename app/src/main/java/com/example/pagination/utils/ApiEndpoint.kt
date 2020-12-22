package com.example.pagination.utils

import com.example.pagination.paging.response.NewsModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {

    /**
     * feed top head line in endless manner
     */
    @GET("top-headlines")
    fun getTopHeadlinesEndless(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsModel?>?

    /**
     * feed top head line with lazy loading
     */
    @GET("top-headlines")
    fun getTopHeadlinesLazy(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<ResponseBody>

}