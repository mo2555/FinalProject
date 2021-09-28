package com.example.finalproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Model.Data
import com.example.finalproject.Model.RestaurantListData
import com.example.mvvmtask.API.ApiHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel(){
    var error_message: MutableLiveData<String> = MutableLiveData()
    var restaurantListData: MutableLiveData<List<RestaurantListData>> = MutableLiveData()

    fun getRestaurantList():LiveData<List<RestaurantListData>>{
        return restaurantListData
    }


    fun getData() {
        ApiHelper.getMyApis("https://android-training.appssquare.com/api/")
            .getData()
            .enqueue(object : Callback<Data> {
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    if(response.isSuccessful ) {
                        var data = response.body()?.data
                        restaurantListData.postValue(data)
                    }
                    else
                    {
                        error_message.postValue("Something error please try again later")
                    }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                error_message.postValue("Something error please try again later")
                }

            })
    }

}

