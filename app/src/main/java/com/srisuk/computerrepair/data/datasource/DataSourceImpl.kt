package com.srisuk.computerrepair.data.datasource

import com.srisuk.computerrepair.data.database.*
import com.srisuk.computerrepair.data.map.HistoryMap
import com.srisuk.computerrepair.data.map.ProfileMap
import com.srisuk.computerrepair.data.models.History
import com.srisuk.computerrepair.data.models.Profile
import com.srisuk.computerrepair.data.request.LoginRequest
import com.srisuk.computerrepair.data.response.HistoryResponse
import com.srisuk.computerrepair.data.response.LoginResponse
import kotlinx.coroutines.selects.select
import org.jetbrains.exposed.sql.*
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

    override fun profile(userId: Int): Profile {
        return transaction {
            addLogger(StdOutSqlLogger)

            (Users innerJoin Agency)
                .slice(
                    Users.user_id,
                    Users.full_name,
                    Users.telephone,
                    Agency.agency_name,
                )
                .select { Users.user_id eq userId }
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

    override fun history(userId: Int): List<History> {
        return transaction {
            addLogger(StdOutSqlLogger)
            (Repair innerJoin Users innerJoin Agency innerJoin Room innerJoin Problem innerJoin Status )
                .slice(

                    Repair.repair_date,
                    Agency.agency_name,
                    Room.room_number,
                    Problem.problem_name,
                    Status.status_name
                )
                .select { Repair.user_id eq userId }
                .andWhere { Users.user_id eq Repair.user_id }
                .map { HistoryMap.toHistory(it) }
        }
    }

}