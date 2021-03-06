package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.Date
import org.jetbrains.exposed.sql.jodatime.date
import org.jetbrains.exposed.sql.jodatime.datetime

object Repair : Table("repair") {
    val repair_id = integer("repair_id").autoIncrement()
    val user_id = integer("user_id").references(Users.user_id)
    val employee_id = integer("employee_id")
    val problem_id = integer("problem_id").references(Problem.problem_id)
    val status_id = integer("status_id").references(Status.status_id)
    val device_id=integer("device_id").references(Device.device_id)
    val repair_date = date("repair_date")
    val detail = varchar("detail", 50)
    val test_result = varchar("test_result", 50)
    val datelong = long("datelong")
    val count_time = long("count_time")
    val enddate = long("enddate")

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Repair.repair_id, name = "repair_id")
}