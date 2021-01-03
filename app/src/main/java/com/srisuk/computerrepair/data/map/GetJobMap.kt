package com.srisuk.computerrepair.data.map

import com.srisuk.computerrepair.data.database.Agency
import com.srisuk.computerrepair.data.database.Problem
import com.srisuk.computerrepair.data.database.Repair
import com.srisuk.computerrepair.data.database.Room
import com.srisuk.computerrepair.data.models.GetJobModel
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.jodatime.datetime

object GetJobMap {
    fun toGetJob(row:ResultRow)=GetJobModel(
        date_job = row[Repair.repair_date],
        agency_job = row[Agency.agency_name],
        room_job =  row[Room.room_number],
        problem_job =  row[Problem.problem_name],
        repair_job=row[Repair.repair_id]

    )

}