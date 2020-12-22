package com.example.pagination.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.pagination.paging.response.NewsModel

class FeedDataSourceFactory : DataSource.Factory<Int, NewsModel.Article>() {

    private val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, NewsModel.Article>>()

    override fun create(): FeedDataSource {
        val itemDataSource = FeedDataSource()
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, NewsModel.Article>> {
        return itemLiveDataSource
    }

}