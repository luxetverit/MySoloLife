package com.example.mysololife.test.httpTest

import android.security.identity.ResultData
import com.google.gson.annotations.SerializedName

//http://58.180.90.33:3000/facredis/searchdata?a={"key":"test:1234:kddi53:20211216144900","likeyn":"Y"}
//{"resultcode":"1a","resultdesc":"ok","resultdata":[{"key":"test:1234:kddi53:20211216144900","value":"1","expiretime":"2206"}]}


data class GetSearchDataModel(
    var key: String,
    var value: String,
    var ttl: String
)

data class PostAddDataModel(
    var key: String?=null,
    var value: String?=null,
    var ttl: String?=null
)

data class ResponseDataModel(
    var resultcode: String,
    var resultdesc: String,
    var resultdata: List<ResultDataModel> = arrayListOf()
)

data class ResultDataModel(
    @SerializedName("key")
    var key: String?,
    @SerializedName("value")
    var value: String?,
    @SerializedName("expiretime")
    var ttl: String?
)
