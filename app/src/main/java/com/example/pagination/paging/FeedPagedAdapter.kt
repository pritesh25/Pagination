package com.example.pagination.paging

import androidx.paging.PagedListAdapter
import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.pagination.R
import com.example.pagination.paging.response.NewsModel
import com.example.pagination.paging.response.NewsModel.Article

class FeedPagedAdapter(private var cxt: Context?) : PagedListAdapter<NewsModel.Article, RecyclerView.ViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsModel.Article>() {
            override fun areItemsTheSame(oldItem: NewsModel.Article, newItem: NewsModel.Article): Boolean {
                return oldItem.url === newItem.url
            }

            override fun areContentsTheSame(oldItem: NewsModel.Article, newItem: NewsModel.Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(cxt).inflate(R.layout.card_apps, p0, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

        val feedModel: NewsModel.Article = this.getItem(p1)!!

        if (p0 is MyViewHolder) {
            p0.tvTitle.text = feedModel.title
            p0.tvDescription.text = feedModel.description
            //Glide.with(cxt!!).load(feedModel.urlToImage).into(p0.ivUrlToImage)
            p0.ivUrlToImage.load(feedModel.urlToImage)
        }
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivUrlToImage: ImageView = view.findViewById(R.id.iv_urlToImage)
        var tvTitle: TextView = view.findViewById(R.id.tv_title)
        var tvDescription: TextView = view.findViewById(R.id.tv_description)
    }
}