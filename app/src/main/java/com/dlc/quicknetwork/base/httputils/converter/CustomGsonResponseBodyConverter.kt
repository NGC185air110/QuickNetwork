package com.dlc.quicknetwork.base.httputils.converter

import com.dlc.quicknetwork.base.httputils.HttpStatus
import com.dlc.quicknetwork.base.httputils.converter.error.ApiException
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.orhanobut.logger.Logger
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/29 17:17
 */
internal class CustomGsonResponseBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        val response = value.string()
        val httpStatus = gson.fromJson(response, HttpStatus::class.java)
        // 这里只是为了检测code是否==8200,所以只解析HttpStatus中的字段,因为只要code和message就可以了
        if (httpStatus.isCodeInvalid) {
            value.close()
            Logger.d("${httpStatus.code}${httpStatus.msg}+++++++++++++++++++")
            //抛出一个RuntimeException, 这里抛出的异常会到Subscriber的onError()方法中统一处理
            throw ApiException(httpStatus.code, httpStatus.msg?:"")
        }
        val contentType = value.contentType()
        val charset =
            if (contentType != null) contentType.charset(StandardCharsets.UTF_8) else StandardCharsets.UTF_8
        val inputStream = ByteArrayInputStream(response.toByteArray())
        val reader = InputStreamReader(inputStream, charset)
        val jsonReader = gson.newJsonReader(reader)

        try {
            return adapter.read(jsonReader)
        } finally {
            value.close()
        }
    }
}