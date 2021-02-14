package com.srisuk.computerrepair.data.request

import org.joda.time.DateTime

data class HistoryRequest(
    val userId:Int,
    val date: DateTime,
    val dateEnd:DateTime
)
