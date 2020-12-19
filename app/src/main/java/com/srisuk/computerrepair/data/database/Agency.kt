package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table

object Agency:Table("agency") {
    val agency_id=integer("agency_id").autoIncrement()
    val agency_type_id=integer("agencytype_id").references(AgencyType.agency_type_id)
    val room_id=integer("room_id")
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Agency.agency_id, name = "agency_id")
}