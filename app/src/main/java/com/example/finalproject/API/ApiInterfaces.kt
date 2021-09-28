package com.example.mvvmtask.API


import com.example.finalproject.Model.Data
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterfaces {

    @GET("restaurants")
    fun getData(): Call<Data>
}