package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table

object Agency_type : Table("agency_type") {
    val agency_type_id = integer("agency_type_id").autoIncrement()
    val agency_name = varchar("agency_name", 50)
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Agency_type.agency_type_id, name = "agency_type_id")
}