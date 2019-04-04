package com.kotlab.supreme.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import com.kotlab.supreme.paging.response.Article

class FeedDataSourceFactory : DataSource.Factory<Int, Article>() {

    private val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Article>>()

    override fun create(): FeedDataSource {
        val itemDataSource = FeedDataSource()
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Article>> {
        return itemLiveDataSource
    }

}