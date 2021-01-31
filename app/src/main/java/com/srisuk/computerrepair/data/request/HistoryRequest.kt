package com.srisuk.computerrepair.data.request

import org.joda.time.DateTime

data class HistoryRequest(
    val userId:Int,
    val date: Long,
    val dateEnd:Long
)
