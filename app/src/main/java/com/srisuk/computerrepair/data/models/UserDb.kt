package com.srisuk.computerrepair.data.models

data class UserDb(
    val userId:Int,
    val userName:String,
    val passWord:String,
    val name: String,
    val telephone:String,
    val role:Int?=null,
    val agency:Int
)
