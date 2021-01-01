package com.srisuk.computerrepair.data.request

import org.joda.time.DateTime

data class InsertRepairRequest(
    val user_id :Int,
    val employee_id :Int?=null,
    val problem_id :Int,
    val status_id :Int,
    val repair_date :DateTime?=null,
    val detail :String?=null,
    val test_result :String?=null,
    val device_id :Int

)
