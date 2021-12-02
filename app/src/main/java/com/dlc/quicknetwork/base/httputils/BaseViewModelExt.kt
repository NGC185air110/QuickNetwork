package com.dlc.quicknetwork.base.httputils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlc.quicknetwork.base.httputils.converter.error.ApiException
import com.dlc.quicknetwork.base.otp.ResultResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * title：
 * description：
 * author：dinglicheng on 2021/12/2 10:34
 */
/**
 * @param block 请求体
 * @param success 成功执行
 * @param error 失败执行(可不实现)
 */
fun <T> ViewModel.request(
    block: suspend () -> ResultResponse<T>,
    success: (T) -> Unit,
    error: (ApiException) -> Unit = {},
): Job {
    return viewModelScope.launch {
        runCatching {
            block()
        }.onSuccess {
            //不需要判断错误码因为我okhttp已经写了拦截
            //可以额外加自己喜欢的状态
            if (it.result != null) {
                success(it.result!!)
            } else {
                throw ApiException(500, "服务器错误")
            }
        }.onFailure {
            //这里可以重新封装一个错误处理方法避免重复代码
            when (it) {
                is HttpException -> {
                }
                is ApiException -> {
                    when {
                        it.isPasswordNoTrue -> {

                        }
                    }
                    error(it)
                }
            }
        }
    }
}