package com.example.pagination.paging.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class NewsModel(
    @SerializedName("articles") var articles: List<Article?>?,
    @SerializedName("status") var status: String?,
    @SerializedName("totalResults") var totalResults: Int?
) {
    @Keep
    data class Article(
        @SerializedName("author") var author: String?,
        @SerializedName("content") var content: String?,
        @SerializedName("description") var description: String?,
        @SerializedName("publishedAt") var publishedAt: String?,
        @SerializedName("source") var source: Source?,
        @SerializedName("title") var title: String?,
        @SerializedName("url") var url: String?,
        @SerializedName("urlToImage") var urlToImage: String?
    ) {
        @Keep
        data class Source(
            @SerializedName("id") var id: Any?,
            @SerializedName("name") var name: String?
        )
    }
}