package com.srisuk.computerrepair.data.models

import org.joda.time.DateTime

data class History(
    val Date: DateTime? = null,
    val Room: String? = null,
    val Agency: String? = null,
    val Status: String? = null,
    val Problem: String? = null,
    val End:Long?=null,
    val Star:Long?=null

)
