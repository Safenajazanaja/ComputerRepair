package com.srisuk.computerrepair.data.datasource

import com.srisuk.computerrepair.data.database.*
import com.srisuk.computerrepair.data.map.*
import com.srisuk.computerrepair.data.models.*
import com.srisuk.computerrepair.data.models.Role
import com.srisuk.computerrepair.data.request.LoginRequest
import com.srisuk.computerrepair.data.response.LoginResponse
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
            }

            if (result == 0) {
                response.success = false
                response.message = "Password incorrect"
            } else {
                val userId = transaction {
                    Users.select { Users.username eq req.username }
                        .andWhere { Users.password eq req.password }
                        .map { it[Users.user_id] }
                        .single()
                }

                response.userId = userId
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
    override fun devices(roomId:Int):List<DeviceModel>{
        return transaction {
            addLogger(StdOutSqlLogger)
            (Device innerJoin Room )
                .slice(
                    Device.device_id,
                    Device.device_code
                )
                .select { Device.room_id eq  roomId }
                .map { DeviceMap.toDeviceMap(it) }
        }
    }
    override fun  checkrole(userId: Int): Role {
        return transaction {
            addLogger(StdOutSqlLogger)
            Users
                .slice(
                    Users.role_id,
                )
                .select { Users.user_id eq userId }
                .map { MapObject.toUser(it) }
                .single()
        }
    }

   override fun roomdevice():List<RoomDeviceModel>{
       return transaction {
           addLogger(StdOutSqlLogger)
              Room
                  .selectAll()
                  .map { RoomDeviceMap.toRoomDevice(it) }
       }
   }
    override fun problemdetail():List<ProblemDetailModel>{
        return transaction {
            addLogger(StdOutSqlLogger)
            Problem
                .selectAll()
                .map { ProblemDetailMap.toProblemDetailMap(it) }
        }
    }
    override fun devicedetail(deviceId:Int):List<DeviceDetailModel>{
        return transaction {
            addLogger(StdOutSqlLogger)
            (DeviceDetail innerJoin Device)
                .slice(
                    DeviceDetail.device_detail_id,
                    DeviceDetail.device_detail_name
                )
                .select { Device.device_id eq deviceId }
                .map { DeviceDetailMap.toDeviceDetail(it) }
        }
    }
}