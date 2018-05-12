package com.lukasvalik.androidcomponentscore

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lukasvalik.kotlinandroidcomponents.repository.NetworkBoundResource

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        airlines.observe(this, Observer {
            Log.d(TAG, "Airlines downloaded " + it?.data?.size)
        })
    }

    private val airlines = object : NetworkBoundResource<List<Airline>, List<Airline>>(App.instance.appExecutors){
        override fun saveCallResult(airlines: List<Airline>) {
            App.instance.cache.airlines = airlines
        }

        override fun shouldFetch(data: List<Airline>?): Boolean {
            return data == null || data.isEmpty()
        }

        override fun loadFromDb() : LiveData<List<Airline>> {
            val airlinesLiveData = MutableLiveData<List<Airline>>()
            airlinesLiveData.value = App.instance.cache.airlines
            return airlinesLiveData
        }

        override fun createCall() = App.instance.webService.getAirlines()

        override fun onFetchFailed() {
            Log.e(TAG, "Airlines fetch failed")
        }
    }.asLiveData()
}
