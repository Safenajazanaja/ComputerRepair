package com.srisuk.computerrepair.data.models

import org.joda.time.DateTime

data class JobModel(
    val date_job: Long? = null,
    val agency_job: String? = null,
    val room_job: String? = null,
    val problem_job: String? = null,
    val repair_id:Int?=null
)
