package com.srisuk.computerrepair.data.database

import org.jetbrains.exposed.sql.Table

object Problem_type : Table("problem_type") {
    val problem_type_id = integer("problem_type_id").autoIncrement()
    val problem_type_name = varchar("problem_type_name", 50)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Problem_type.problem_type_id, name = "problem_type_id")
}