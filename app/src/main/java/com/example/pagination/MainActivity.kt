package com.example.pagination

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagination.paging.FeedPagedAdapter
import com.example.pagination.paging.FeedViewModel

class MainActivity : AppCompatActivity() {

    private val feedViewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

    }

    private fun initData() {
        val feedListAdapter = FeedPagedAdapter(applicationContext)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        feedViewModel.itemPagedList.observe(this, {
            feedListAdapter.submitList(it)
        })
        recyclerView.adapter = feedListAdapter
    }
}