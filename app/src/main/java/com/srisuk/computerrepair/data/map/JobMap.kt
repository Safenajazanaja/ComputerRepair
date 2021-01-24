package com.srisuk.computerrepair.data.map

import com.srisuk.computerrepair.data.database.Agency
import com.srisuk.computerrepair.data.database.Problem
import com.srisuk.computerrepair.data.database.Repair
import com.srisuk.computerrepair.data.database.Room
import com.srisuk.computerrepair.data.models.GetJobModel
import com.srisuk.computerrepair.data.models.JobModel
import com.srisuk.computerrepair.data.models.YoujobModel
import org.jetbrains.exposed.sql.ResultRow

object JobMap {
    fun toJob(row:ResultRow)=JobModel(
        date_job = row[Repair.repair_date],
        agency_job = row[Agency.agency_name],
        room_job =  row[Room.room_number],
        problem_job =  row[Problem.problem_name],
        repair_id=row[Repair.repair_id]
    )
    fun toGetJob(row: ResultRow)=GetJobModel(
        date_job = row[Repair.repair_date],
        agency_job = row[Agency.agency_name],
        room_job =  row[Room.room_number],
        problem_job =  row[Problem.problem_name],
    )
    fun toyoujob(row: ResultRow)=YoujobModel(
        date_job = row[Repair.repair_date],
        agency_job = row[Agency.agency_name],
        room_job =  row[Room.room_number],
        problem_job =  row[Problem.problem_name],
        repair_id=row[Repair.repair_id]
    )

}