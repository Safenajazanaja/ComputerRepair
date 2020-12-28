package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table

object Agency : Table("agency") {
    val agency_id = integer("agency_id").autoIncrement()
    val agency_name = varchar("agency_name", 50)
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Agency.agency_id, name = "PK_agency_id")
}