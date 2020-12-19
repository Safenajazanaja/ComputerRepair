package com.srisuk.computerrepair.data.response

data class BaseResponse(
    var success: Boolean = false,
    var message: String? = null,
    var userId: Int? = null,
)
