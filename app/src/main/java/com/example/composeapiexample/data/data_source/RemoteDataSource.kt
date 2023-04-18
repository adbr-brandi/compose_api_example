package com.example.composeapiexample.data.data_source

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TAG: String = "REMOTE DATA SOURCE"

fun getJSONData(query: String = "hello", list: MutableList<String>) {
    val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://dapi.kakao.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val retrofitAPI: KakaoAPI = retrofit.create(KakaoAPI::class.java)

    val call: Call<Map<String, Any>> = retrofitAPI.getSearchImage(
        query = query, token = "KakaoAK 0940c8803b43f3a1f436cb7e88d1f3a5"
    )

    call.enqueue(object : Callback<Map<String, Any>?> {
        override fun onResponse(
            call: Call<Map<String, Any>?>,
            response: Response<Map<String, Any>?>,
        ) {
            Log.d(TAG, "onResponse 성공")
            if (response.isSuccessful) {
                val results = response.body()!!
                val docs = results["documents"] as List<*>
                for (doc in docs) {

                    Log.d(TAG, "doc: $doc")
                }
            }
        }

        override fun onFailure(call: Call<Map<String, Any>?>, t: Throwable) {
            Log.d(TAG, "onFailure 실패")
        }

    })
}

