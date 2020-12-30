package com.srisuk.computerrepair.data.map

import com.srisuk.computerrepair.data.database.Problem
import com.srisuk.computerrepair.data.models.ProblemDetailModel
import org.jetbrains.exposed.sql.ResultRow

object ProblemDetailMap {
    fun toProblemDetailMap(row: ResultRow)=ProblemDetailModel(
        problemId = row[Problem.problem_id],
        problem_name = row[Problem.problem_name]
    )
}