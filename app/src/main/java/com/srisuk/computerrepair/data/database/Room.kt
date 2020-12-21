package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table

object Room : Table("room") {
    val room_id = integer("room_id").autoIncrement()
    val room_detail_id = integer("room_detail_id").references(Room_detail.room_detail_id)
    val device_id = integer("device_id").references(Device.device_id)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Room.room_id, name = "room_id")
}