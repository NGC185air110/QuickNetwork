package com.dlc.quicknetwork.base.httputils

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class HttpStatus : Serializable {
    @SerializedName("msg")
    val msg: String? = null

    @SerializedName("code")
    val code: Int = 8200

    val isCodeInvalid: Boolean
        get() = code != 200
}
