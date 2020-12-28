package com.srisuk.computerrepair.data.map

import com.srisuk.computerrepair.data.database.Agency
import com.srisuk.computerrepair.data.database.Users
import com.srisuk.computerrepair.data.models.Profile
import org.jetbrains.exposed.sql.ResultRow

object ProfileMap {
    fun toProfile(row: ResultRow) = Profile(
        userId = row[Users.user_id],
        name = row[Users.full_name],
        telephone = row[Users.telephone],
        agency_name = row[Agency.agency_name],
    )

}