package com.example.mysololife.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.mysololife.R
import com.example.mysololife.databinding.ActivityTesthttpBinding
import com.example.mysololife.test.httpTest.*
import okhttp3.Cache.Companion.key
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//http://58.180.90.33:3000/facredis/searchdata?a={"key":"test:1234:kddi53:20211216144900","likeyn":"Y"}
//{"resultcode":"1a","resultdesc":"ok","resultdata":[{"key":"test:1234:kddi53:20211216144900","value":"1","expiretime":"2206"}]}

class TestHttpActivity : AppCompatActivity() {

    val binding by lazy { ActivityTesthttpBinding.inflate(layoutInflater) }
    val api = INetworkService.create();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.getBtn.setOnClickListener {
            var inlikeyn = "Y"
            var inKey = binding.inKey.text.toString()
            var URIString = "{\"key\" : \"${inKey}\", \"likeyn\" : \"${inlikeyn}\"}"
            Log.d("http_log", URIString)

            api.get_searchdata(URIString).enqueue(object : Callback<ResponseDataModel> {
                override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>

                ) {
                    Log.d("http_log", response.toString())
                    Log.d("http_log", response.body().toString())

                    //binding.result.text = response.body().toString()
                    response.body()?.let{
                        val resultcode = it.resultcode
                        val resultdesc = it.resultdesc
                        val resultdata = it.resultdata
                        val resultJSON = response.body()

                        val list : List<ResultDataModel> = resultJSON!!.resultdata
                        binding.result.text = "{\"resultcode\" : \"${resultcode}\", \"resultdesc\" : \"${resultdesc}\", \"resultdata\" : \"${list}\"}}"

                        //binding.result.text = "{\"resultcode\" : \"${resultcode}\", \"resultdesc\" : \"${resultdesc}\", \"resultdata\" : \"${resultdata}\"}}"
                    }


                    //if(!response.body().toString().isEmpty())


                }

                override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                    Log.d("http_log_fail", t.message.toString())
                }
            })
        }

        binding.postBtn.setOnClickListener {
            var inKey = binding.inKey.text.toString()
            var inValue = binding.inValue.text.toString()
            var inTTL = binding.inTTL.text.toString()
            var URIString = "{\"key\" : \"${inKey}\", \"value\" : \"${inValue}\", \"expiretime\" : \"${inTTL}\"}"
            api.post_adddata(URIString).enqueue(object : Callback<ResponseDataModel> {
                override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
                ) {
                    Log.d("http_log", response.toString())
                    Log.d("http_log", response.body().toString())
                    val userlist = response.body()
                    if(!response.body().toString().isEmpty())
                        binding.result.text = response.body().toString()
                }

                override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                    Log.d("http_log_fail", t.message.toString())
                }
            })


        }

    }

}