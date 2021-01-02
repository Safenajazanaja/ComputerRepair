package com.srisuk.computerrepair.data.request

import org.joda.time.DateTime

data class InsertRepairRequest(
    val user_id :Int?=null,
    val employee_id :Int?=null,
    val problem_id :Int?=null,
    val status_id :Int?=null,
    val repair_date :DateTime?=null,
    val detail :String?=null,
    val test_result :String?=null,
    val device_id :Int?=null

)
