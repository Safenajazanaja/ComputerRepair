package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table

object Status : Table("status") {
    val status_id = integer("status_id").autoIncrement()
    val status_name = varchar("status_name", 50)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Status.status_id, name = "status_id")
}