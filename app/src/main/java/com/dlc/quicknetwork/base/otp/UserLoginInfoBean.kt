package com.dlc.quicknetwork.base.otp

import java.io.Serializable

/**
 * title：
 * description：
 * author：dinglicheng on 2021/6/15 13:10
 */
class UserLoginInfoBean : Serializable {
    /**
     * {
    "expiresIn": 43200,
    "unionId": null,
    "mobile": "157****7740",
    "nickname": "157****7740",
    "type": 0,
    "token": "6634f52da61fbdf24e149cc0c2d08217",
    "refreshToken": "4896e8d4dfc59aa52b80fa387cdc7ca2"
    }
     */
    var expiresIn: Int = 0
    var mobile: String? = null
    var nickname: String? = null
    var type: Int? = null
    var token: String? = null

}