package com.star.chartapplesson15.data.restApi

import com.star.chartapplesson15.data.local.entity.ProductData
import com.star.chartapplesson15.model.ValueData
import com.star.chartapplesson15.model.ResponseData
import com.star.chartapplesson15.data.local.entity.UsersData
import retrofit2.Call
import retrofit2.http.GET

interface StatisticsDataApi {

    @GET("analytic/balance")
    fun getBalance() : Call<ResponseData<List<ValueData>>>

    @GET("analytic/tasks")
    fun getTasks(): Call<ResponseData<List<ValueData>>>

    @GET("analytic/users")
    fun getUsers(): Call<ResponseData<List<UsersData>>>

    @GET("analytic/workers")
    fun getWorkers(): Call<ResponseData<List<ValueData>>>

    @GET("analytic/products")
    fun getProducts(): Call<ResponseData<List<ProductData>>>

    @GET("analytic/balance/all")
    fun getAllBalance() : Call<ResponseData<List<ValueData>>>

    @GET("analytic/tasks/all")
    fun getAllTasks(): Call<ResponseData<List<ValueData>>>

    @GET("analytic/users/all")
    fun getAllUsers(): Call<ResponseData<List<UsersData>>>

    @GET("analytic/workers/all")
    fun getAllWorkers(): Call<ResponseData<List<ValueData>>>

    @GET("analytic/products/all")
    fun getAllProducts(): Call<ResponseData<List<ProductData>>>

}