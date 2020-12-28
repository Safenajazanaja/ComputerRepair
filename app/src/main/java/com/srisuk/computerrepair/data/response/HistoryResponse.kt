package com.srisuk.computerrepair.data.response

import com.srisuk.computerrepair.data.models.History

data class HistoryResponse(

    val histories: List<History>,
    val message: String,
    val success: Boolean
)
