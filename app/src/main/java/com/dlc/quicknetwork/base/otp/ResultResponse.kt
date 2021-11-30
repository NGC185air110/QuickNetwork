package com.dlc.quicknetwork.base.otp

import java.io.Serializable

class ResultResponse<T> : Serializable {

    var version: String? = null
    var requestId: String? = null
    var code: Int = 0
    var msg: String? = null
    var result: T? = null

    constructor(version: String, requestId: String, code: Int, msg: String, result: T) {
        this.version = version
        this.requestId = requestId
        this.code = code
        this.msg = msg
        this.result = result
    }
}
