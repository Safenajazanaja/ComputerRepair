package com.srisuk.computerrepair.data.request

data class SaveJogRequest(
    val status_id :Int,
    val test_result:String,
    val repair_job:Int
)

