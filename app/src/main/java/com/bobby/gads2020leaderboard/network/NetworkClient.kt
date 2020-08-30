package com.bobby.gads2020leaderboard.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkClient {

    private var retrofit: Retrofit? = null

    private var moshi: Moshi? = null

    fun getInstance(getLeaders: Boolean, submitProject: Boolean): Retrofit? {
        var base_url = ""

        when (true) {

            getLeaders -> {
                base_url = "https://gadsapi.herokuapp.com/"
            }
            submitProject -> {

                base_url = "https://docs.google.com/forms/d/e/"
            }
        }

        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Log.i("PUMPS", base_url)

        retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(MoshiConverterFactory.create(moshi!!))
            .build()


        return retrofit

    }

    companion object{

        fun getMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory()).build()
        }
    }

}