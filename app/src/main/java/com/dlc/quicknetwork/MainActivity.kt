package com.dlc.quicknetwork

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.blankj.utilcode.util.GsonUtils
import com.dlc.quicknetwork.base.ApiService
import com.dlc.quicknetwork.base.httputils.RetrofitUtils
import com.dlc.quicknetwork.base.secarity.AESClientUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody

val mApi: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    RetrofitUtils.retrofit.create(ApiService::class.java)
}

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var text: TextView = findViewById(R.id.tv_a)
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["type"] = 1
        hashMap["passwd"] = AESClientUtil.Encrypt("123456")
        hashMap["loginId"] = AESClientUtil.Encrypt("15727877740")
        GlobalScope.launch {
            kotlin.runCatching {
                mApi.userLogin(getJsonRequestBody(getParams(hashMap)))
            }.onSuccess {
                it.result
            }.onFailure {
                it.message
            }
        }


    }

    /**
     * 后端规定统一封装接口
     */
    fun getParams(map: Map<String, Any>): Map<String, Any> {
        val params: HashMap<String, Any> = HashMap()
        params["params"] = map
        return params
    }

    fun getJsonRequestBody(obj: Any): RequestBody? {
        return RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            GsonUtils.toJson(obj)
        )
    }
}