package com.srisuk.computerrepair.data.datasource

import com.srisuk.computerrepair.data.database.Agency
import com.srisuk.computerrepair.data.database.Agency_type
import com.srisuk.computerrepair.data.database.Users
import com.srisuk.computerrepair.data.map.ProfileMap
import com.srisuk.computerrepair.data.models.Profile
import com.srisuk.computerrepair.data.request.LoginRequest
import com.srisuk.computerrepair.data.response.LoginResponse
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object DataSourceImpl : DataSource {
    override fun login(req: LoginRequest): LoginResponse {
        val response = LoginResponse()

        if (req.username.isBlank()) {
            response.message = "Username empty"
        } else if (req.password.isBlank()) {
            response.message = "Password empty"
        } else {
            val result = transaction {
                addLogger(StdOutSqlLogger)

                Users.select { Users.username eq req.username }
                    .andWhere { Users.password eq req.password }
                    .count()
                    .toInt()
//                    .map { MapObject.toUser(it) }
//                    .single()
            }
            if (result == 0) {
                response.success = false
                response.message = "Password incorrect"
            } else {
                response.success = true
                response.message = "Login success"
            }
        }
        return response
    }

    override fun profile(userId:Int): Profile {
        return transaction {
            addLogger(StdOutSqlLogger)

            (Users innerJoin Agency innerJoin Agency_type)
                .slice(
                    Users.userId,
                    Users.name,
                    Users.telephone,
                    Agency_type.agency_name,
                )
                .select { Users.userId eq userId }
                .map { ProfileMap.toProfile(it) }
//                .map {row->
//                    Profile(
//                        userId = row[Users.userId],
//                        name = row[Users.name],
//                        telephone = row[Users.telephone],
//                        agency_name = row[AgencyType.agency_name],
//                    )
//                }
                .single()
        }
    }

}