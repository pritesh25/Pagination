package com.kotlab.supreme.paging

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.example.pagination.RetrofitClient
import com.example.pagination.Utils
import com.example.pagination.Utils.Companion.NEWS_URL
import com.kotlab.supreme.paging.response.ApiResponse
import com.kotlab.supreme.paging.response.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedDataSource : PageKeyedDataSource<Int, Article>() {

    private val mTag = FeedDataSource::class.java.simpleName

    companion object {
        private const val PAGE: Int = 1
        const val PAGE_SIZE: Int = 2
        private const val COUNTRY: String = "in"

    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        Log.d(mTag, "loadInitial called")
        val apiServices = RetrofitClient().sampleTest(NEWS_URL).create(RetrofitClient.Api::class.java)
        val result: Call<ApiResponse> = apiServices.getTopHeadlinesEndless(PAGE, PAGE_SIZE, COUNTRY, Utils.KEY_NEWS)
        result.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                if (response.body() != null) {
                    callback.onResult(response.body()!!.articles!!, null, PAGE + 1)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

        Log.d(mTag, "loadBefore called")
        val apiServices = RetrofitClient().sampleTest(NEWS_URL).create(RetrofitClient.Api::class.java)
        val result: Call<ApiResponse> =
            apiServices.getTopHeadlinesEndless(params.key, PAGE_SIZE, COUNTRY, Utils.KEY_NEWS)
        result.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                val adjacentKey = (if (params.key > 1) params.key - 1 else null)!!.toInt()
                if (response.body() != null) {
                    //val key = if (params.key > 1) params.key - 1 else null
                    callback.onResult(response.body()!!.articles!!, adjacentKey)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

        Log.d(mTag, "loadAfter called")

        val apiServices = RetrofitClient().sampleTest(NEWS_URL).create(RetrofitClient.Api::class.java)
        val result: Call<ApiResponse> =
            apiServices.getTopHeadlinesEndless(params.key, PAGE_SIZE, COUNTRY, Utils.KEY_NEWS)
        result.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                if (response.body() != null) {
                    callback.onResult(response.body()!!.articles!!, params.key + 1)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            }
        })
    }
}