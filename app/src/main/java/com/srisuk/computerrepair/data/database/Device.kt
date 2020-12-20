package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table

object Device:Table("device") {
    val device_id=integer("device_id").autoIncrement()
    val device_code=varchar("device",50)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Device.device_id,name = "device_id")
}