package com.srisuk.computerrepair.data.map

import com.srisuk.computerrepair.data.database.Agency
import com.srisuk.computerrepair.data.database.AgencyType
import com.srisuk.computerrepair.data.database.Users
import com.srisuk.computerrepair.data.models.Profile
import com.srisuk.computerrepair.data.models.UserDb
import org.jetbrains.exposed.sql.ResultRow

object ProfileMap {
    fun toProfile(row: ResultRow) = Profile(
        userId = row[Users.userId],
        name = row[Users.name],
        telephone = row[Users.telephone],
        agency_name = row[AgencyType.agency_name],
    )

}