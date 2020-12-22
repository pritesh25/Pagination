package com.example.pagination.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.pagination.paging.response.NewsModel

class FeedViewModel : ViewModel() {

    var itemPagedList: LiveData<PagedList<NewsModel.Article>>
    private var liveDataSource: LiveData<PageKeyedDataSource<Int, NewsModel.Article>>


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