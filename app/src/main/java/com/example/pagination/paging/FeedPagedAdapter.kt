package com.kotlab.supreme.paging

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pagination.R
import com.kotlab.supreme.paging.response.Article

class FeedPagedAdapter(private var cxt: Context?) : PagedListAdapter<Article, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url === newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(cxt).inflate(R.layout.card_apps, p0, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

        val feedModel: Article = this.getItem(p1)!!

        if (p0 is MyViewHolder) {
            p0.tvTitle.text = feedModel.title
            p0.tvDescription.text = feedModel.description
            Glide.with(cxt!!).load(feedModel.urlToImage).into(p0.ivUrlToImage)
        }
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivUrlToImage: ImageView = view.findViewById(R.id.iv_urlToImage)
        var tvTitle: TextView = view.findViewById(R.id.tv_title)
        var tvDescription: TextView = view.findViewById(R.id.tv_description)
    }
}