package com.star.chartapplesson15.model


data class ResponseData<T>(
    val status: String,
    val message: String = "Successful",
    val data: T? = null
)