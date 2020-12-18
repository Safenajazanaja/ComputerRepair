package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table

object Users :Table("user"){

    val userId= integer("user_id").autoIncrement()
    val role_Id=integer("role_id")
    val agency_id=integer("agency_id")
    val username= varchar("username",50)
    val password= varchar("password",10)
    val name= varchar("name",50)
    val telephone=varchar("telephone",10)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(userId, name = "User_Id")
}