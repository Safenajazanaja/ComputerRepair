package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.date

object Repair : Table("repair") {
    val repair_id = integer("repair_id").autoIncrement()
    val user_id = integer("user_id").references(Users.user_id)
    val employee_id = integer("employee_id").references(Users.user_id)
    val problem_id = integer("problem_id").references(Problem.problem_id)
    val status_id = integer("status_id").references(Status.status_id)
    val repair_dete = date("repair_date")
    val detail = varchar("varchar", 50)
    val test_result = varchar("test_result", 50)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Repair.repair_id, name = "repair_id")
}