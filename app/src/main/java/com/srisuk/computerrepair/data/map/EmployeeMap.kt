package com.srisuk.computerrepair.data.map

import com.srisuk.computerrepair.data.database.Repair
import com.srisuk.computerrepair.data.models.EmployeeModel
import org.jetbrains.exposed.sql.ResultRow

object EmployeeMap{
    fun toEmployeeMap(row: ResultRow) =EmployeeModel(
        id_emp = row[Repair.employee_id]
    )
}