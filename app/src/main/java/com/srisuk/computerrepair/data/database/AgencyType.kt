package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table

object AgencyType:Table("agency_type") {
    val agency_type_id=integer("agencytype_id").autoIncrement()
    val agency_name=varchar("agency_name",50)
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(AgencyType.agency_type_id, name = "agencytype_id")
}