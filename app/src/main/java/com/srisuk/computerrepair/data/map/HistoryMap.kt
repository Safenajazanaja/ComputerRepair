package com.srisuk.computerrepair.data.map


import com.srisuk.computerrepair.data.database.*
import com.srisuk.computerrepair.data.models.History

import org.jetbrains.exposed.sql.ResultRow

object HistoryMap {
    fun toHistory(row: ResultRow) = History(
        Date = row[Repair.repair_date],
        Agency = row[Agency.agency_name],
        Room = row[Room.room_number],
        Problem = row[Problem.problem_name],
        Status = row[Status.status_name],

        )
}