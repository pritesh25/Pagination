package com.example.pagination.paging.response

import okhttp3.ResponseBody

class NewsModelResponse {
    var model: NewsModel?
    var responseErrorBody: ResponseBody?
    var error: Throwable?
    var statusCode: Int?

    constructor(model: NewsModel?, statusCode: Int?) {
        this.model = model
        this.statusCode = statusCode
        this.responseErrorBody = null
        this.error = null
    }

    constructor(responseBody: ResponseBody?, statusCode: Int?) {
        this.responseErrorBody = responseBody
        this.statusCode = statusCode
        this.error = null
        this.model = null
    }

    constructor(error: Throwable?) {
        this.error = error
        this.statusCode = null
        this.responseErrorBody = null
        this.model = null
    }
}