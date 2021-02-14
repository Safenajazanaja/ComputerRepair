package com.srisuk.computerrepair.data.models

import org.joda.time.DateTime

data class GetJobModel(
    val date_job: DateTime? = null,
    val agency_job: String? = null,
    val room_job: String? = null,
    val problem_job: String? = null,
    val count_time :Long
)

