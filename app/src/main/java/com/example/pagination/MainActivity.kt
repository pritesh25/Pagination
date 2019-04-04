package com.example.pagination

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.kotlab.supreme.paging.FeedPagedAdapter
import com.kotlab.supreme.paging.FeedViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var feedViewModel: FeedViewModel
    private lateinit var feedListAdapter: FeedPagedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

    }

    private fun initData() {
        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        feedListAdapter = FeedPagedAdapter(applicationContext)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
        feedViewModel.itemPagedList.observe(this, Observer {
            feedListAdapter.submitList(it)
        })
        recyclerView.adapter = feedListAdapter
    }
}