package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table

object Room_detail : Table("room_detail") {
    val room_detail_id = integer("room_detail_id").autoIncrement()
    val room_number = varchar("room_number", 50)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(Room_detail.room_detail_id, name = "room_detail_id")

}