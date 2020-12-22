package com.example.pagination.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.pagination.utils.RetrofitClient
import com.example.pagination.utils.Utils
import com.example.pagination.utils.Utils.Companion.NEWS_URL
import com.example.pagination.paging.response.NewsModel
import com.example.pagination.utils.ApiEndpoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedDataSource : PageKeyedDataSource<Int, NewsModel.Article>() {

    private val mTag = FeedDataSource::class.java.simpleName
    private val apiServices = RetrofitClient().sampleTest(NEWS_URL)?.create(ApiEndpoint::class.java)

    companion object {
        private const val PAGE: Int = 1
        const val PAGE_SIZE: Int = 2
        private const val COUNTRY: String = "in"

    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsModel.Article>
    ) {
        Log.d(mTag, "loadInitial called")

        apiServices?.getTopHeadlinesEndless(PAGE, PAGE_SIZE, COUNTRY, Utils.KEY_NEWS)
            ?.enqueue(object : Callback<NewsModel?> {
                override fun onResponse(call: Call<NewsModel?>, response: Response<NewsModel?>) {
                    response.body()?.let {
                        callback.onResult(it.articles!!, null, PAGE + 1)
                    }
                }

                override fun onFailure(call: Call<NewsModel?>, t: Throwable) {

                }
            })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, NewsModel.Article>
    ) {

        Log.d(mTag, "loadBefore called")
        apiServices?.getTopHeadlinesEndless(params.key, PAGE_SIZE, COUNTRY, Utils.KEY_NEWS)
            ?.enqueue(object : Callback<NewsModel?> {
                override fun onResponse(call: Call<NewsModel?>, response: Response<NewsModel?>) {
                    val adjacentKey = (if (params.key > 1) params.key - 1 else null)!!.toInt()
                    response.body()?.let {
                        callback.onResult(it.articles!!, adjacentKey)
                    }
                }

                override fun onFailure(call: Call<NewsModel?>, t: Throwable) {

                }
            })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, NewsModel.Article>
    ) {

        Log.d(mTag, "loadAfter called")

        apiServices?.getTopHeadlinesEndless(params.key, PAGE_SIZE, COUNTRY, Utils.KEY_NEWS)
            ?.enqueue(object : Callback<NewsModel?> {
                override fun onResponse(call: Call<NewsModel?>, response: Response<NewsModel?>) {
                    response.body()?.let {
                        callback.onResult(it.articles!!, params.key + 1)
                    }
                }

                override fun onFailure(call: Call<NewsModel?>, t: Throwable) {
                }
            })
    }
}