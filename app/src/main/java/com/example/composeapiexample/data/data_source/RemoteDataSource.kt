package com.example.composeapiexample.data.data_source

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun getJSONData(query: String = "hello", list: MutableList<String>) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val retrofitAPI = retrofit.create(KakaoAPI::class.java)

    val call: Call<Map<String, Any>> = retrofitAPI.getSearchImage(
        query = query, authorization = "KakaoAK 0940c8803b43f3a1f436cb7e88d1f3a5"
    )

    call.enqueue(object : Callback<Map<String, Any>?> {
        override fun onResponse(
            call: Call<Map<String, Any>?>,
            response: Response<Map<String, Any>?>,
        ) {
            if (response.isSuccessful) {
                val results = response.body()!!
                for (i in 0 until results.size) {
                    list.add(results.toString())
                }
            }
        }

        override fun onFailure(call: Call<Map<String, Any>?>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })
}

