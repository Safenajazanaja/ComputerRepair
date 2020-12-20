package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table

object Agency : Table("agency") {
    val agency_id = integer("agency_id").autoIncrement()
    val agency_type_id = integer("agency_type_id").references(Agency_type.agency_type_id)
    val room_id = integer("room_id").references(Room.room_id)
    val agency_name = varchar("agency_name", 50)
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Agency.agency_id, name = "agency_id")
}