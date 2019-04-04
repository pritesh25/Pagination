package com.kotlab.supreme.paging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import com.kotlab.supreme.paging.response.Article

class FeedViewModel : ViewModel() {

    var itemPagedList: LiveData<PagedList<Article>>
    private var liveDataSource: LiveData<PageKeyedDataSource<Int, Article>>


    init {
        val itemDataSourceFactory = FeedDataSourceFactory()
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(FeedDataSource.PAGE_SIZE)
            .build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }
}