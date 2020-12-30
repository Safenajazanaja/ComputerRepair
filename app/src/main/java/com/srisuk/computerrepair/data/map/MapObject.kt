package com.srisuk.computerrepair.data.map
import com.srisuk.computerrepair.data.database.Users
import com.srisuk.computerrepair.data.models.Role
import org.jetbrains.exposed.sql.ResultRow

object MapObject {
    fun toUser(row: ResultRow) = Role(
    role = row[Users.role_id]
    )
}