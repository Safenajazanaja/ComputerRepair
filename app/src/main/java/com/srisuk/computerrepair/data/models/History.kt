package com.srisuk.computerrepair.data.models

import org.joda.time.DateTime

data class History(
    val Date: Long? = null,
    val Room: String? = null,
    val Agency: String? = null,
    val Status: String? = null,
    val Problem: String? = null,
)
