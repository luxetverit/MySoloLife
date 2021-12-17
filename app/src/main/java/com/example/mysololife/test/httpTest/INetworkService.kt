package com.example.mysololife.test.httpTest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface INetworkService {

    //http://58.180.90.33:3000/facredis/searchdata?a={"key":"test:1234:kddi53:20211216144900","likeyn":"Y"}
    //{"resultcode":"1a","resultdesc":"ok","resultdata":[{"key":"test:1234:kddi53:20211216144900","value":"1","expiretime":"2206"}]}

    fun rawJSON() {
        val jsonObject = JSONObject()
        jsonObject.put("key", "test")
        jsonObject.put("likeyn", "Y")
    }

    @GET("searchdata")
    fun get_searchdata(@Query("a") query: String): Call<ResponseDataModel>

    @POST("adddata")
    fun post_adddata(@Query("a") query: String): Call<ResponseDataModel>

    companion object {
        private const val BASE_URL = "http://58.180.90.33/facredis/"

        fun create(): INetworkService {

            val gson : Gson = GsonBuilder().setLenient().create();

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(INetworkService::class.java)

        }
    }


}
