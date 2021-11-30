package com.dlc.quicknetwork.base

import com.dlc.quicknetwork.base.otp.ResultResponse
import com.dlc.quicknetwork.base.otp.UserLoginInfoBean
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * title：
 * description：
 * author：dinglicheng on 2021/11/30 11:01
 */
interface ApiService {
    /**
     * OTP登录
     */
    @POST("/uas/user/login_by_code")
    suspend fun userOTPLogin(@Body requestBody: RequestBody?): ResultResponse<UserLoginInfoBean>

    @POST("/uas/user/login")
    suspend fun userLogin(@Body requestBody: RequestBody?): ResultResponse<UserLoginInfoBean>
}