package com.dlc.quicknetwork.base.httputils

import com.dlc.quicknetwork.app.BaseApplication
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * title：
 * description：Okhttp3Utils单例
 * author：dinglicheng on 2021/6/11 11:11
 */
object Okhttp3Utils {
    private var mOkHttpClient: OkHttpClient? = null
    private val cacheDirectory =
        File(BaseApplication.context.applicationContext.cacheDir.absolutePath, "MoneyTree")
    private val cache = Cache(cacheDirectory, (10 * 1024 * 1024).toLong())
    val okHttpClient: OkHttpClient
        get() {
            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .cache(cache)
                    .build()
            }
            return mOkHttpClient!!
        }
}