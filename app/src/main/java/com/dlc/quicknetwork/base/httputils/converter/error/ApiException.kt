package com.dlc.quicknetwork.base.httputils.converter.error

/**
 * title：
 * description：枚举错误建议枚举那些需要处理的错误
 * author：dinglicheng on 2021/3/29 17:53
 */
class ApiException(private val mErrorCode: Int, errorMessage: String) :
    RuntimeException(errorMessage) {
    //服务器错误
    val isNetWorkError: Boolean
        get() = mErrorCode == 8999

    //不支持的mime请求类型
    val isMimeError: Boolean
        get() = mErrorCode == 990001

    //请求contentType 错误
    val isContentTypeError: Boolean
        get() = mErrorCode == 990002

    //登录失效
    val isTokenError: Boolean
        get() = mErrorCode == 990502

    val isNetWorkNull:Boolean
        get() = mErrorCode == 990506
}