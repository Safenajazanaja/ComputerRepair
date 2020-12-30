package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table

object Users : Table("user") {

    val user_id = integer("user_id").autoIncrement()
    val role_id = integer("role_id").references(Role.role_id)
    val agency_id = integer("agency_id").references(Agency.agency_id)
    val room_id = integer("room_id").references(Room.room_id)
    val username = varchar("username", 50)
    val password = varchar("password", 10)
    val full_name = varchar("full_name", 50)
    val telephone = varchar("telephone", 10)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(user_id, name = "user_id")
}