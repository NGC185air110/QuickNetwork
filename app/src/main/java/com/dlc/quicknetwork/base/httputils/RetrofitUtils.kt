package com.dlc.quicknetwork.base.httputils

import com.dlc.quicknetwork.base.httputils.converter.CustomGsonConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * title：
 * description：Retrofit单例
 * author：dinglicheng on 2021/6/11 13:19
 */
object RetrofitUtils {

    //服务器路径
    val apiServer = "http://demo-sdcloud.topxlc.com"//请求地址
    private var mRetrofit: Retrofit? = null
    private var mOkHttpClient: OkHttpClient? = null

    val retrofit: Retrofit
        get() {
            if (null == mRetrofit) {
                if (null == mOkHttpClient) {
                    mOkHttpClient = Okhttp3Utils.okHttpClient
                }
                mRetrofit = Retrofit.Builder()
                    .baseUrl("$apiServer/")
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .client(mOkHttpClient!!)
                    .build()

            }
            return mRetrofit!!
        }
}