package com.dlc.quicknetwork.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.GsonUtils
import com.dlc.quicknetwork.base.ApiService
import com.dlc.quicknetwork.base.httputils.RetrofitUtils
import com.dlc.quicknetwork.base.httputils.request
import com.dlc.quicknetwork.base.otp.UserLoginInfoBean
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * title：
 * description：
 * author：dinglicheng on 2021/12/2 10:27
 */

//这里写在通用基类BaseActivity
val mApi: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    RetrofitUtils.retrofit.create(ApiService::class.java)
}

class MainViewModel : ViewModel() {

    var userData: MutableLiveData<UserLoginInfoBean> = MutableLiveData()

    fun getUserData(hashMap: HashMap<String, Any>) {
        request({ mApi.userLogin(getJsonRequestBody(getParams(hashMap))) }, {
            userData.value = it
        }, {
            //失败了
        })
    }

    /**
     * 后端规定统一封装接口
     */
    private fun getParams(map: Map<String, Any>): Map<String, Any> {
        val params: HashMap<String, Any> = HashMap()
        params["params"] = map
        return params
    }

    private fun getJsonRequestBody(obj: Any): RequestBody? {
        return RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            GsonUtils.toJson(obj)
        )
    }
}