package com.srisuk.computerrepair.data.map

import com.srisuk.computerrepair.data.database.Agency
import com.srisuk.computerrepair.data.models.AgencyNameModel
import org.jetbrains.exposed.sql.ResultRow

object AgencyMap {
    fun toAgencyMap(row: ResultRow)=AgencyNameModel(
        agency_name = row[Agency.agency_name]
    )
}