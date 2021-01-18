package com.srisuk.computerrepair.data.datasource

import android.content.ContentValues
import com.srisuk.computerrepair.data.database.*
import com.srisuk.computerrepair.data.map.*
import com.srisuk.computerrepair.data.models.*
import com.srisuk.computerrepair.data.models.Role
import com.srisuk.computerrepair.data.request.AcceptRequest
import com.srisuk.computerrepair.data.request.InsertRepairRequest
import com.srisuk.computerrepair.data.request.LoginRequest
import com.srisuk.computerrepair.data.request.SaveJogRequest
import com.srisuk.computerrepair.data.response.AcceptResponse
import com.srisuk.computerrepair.data.response.BaseResponse
import com.srisuk.computerrepair.data.response.LoginResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

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



    override fun checkagency(userId: Int): AgencyNameModel {
        return transaction {
            addLogger(StdOutSqlLogger)
            (Users innerJoin Agency)
                .slice(
                    Agency.agency_name,
                    Agency.agency_id
                )
                .select { Users.user_id eq userId }
                .map { AgencyMap.toAgencyMap(it) }
                .single()
        }
    }

    override fun devices(roomId: Int): List<DeviceModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            (Device innerJoin Room)
                .slice(
                    Device.device_id,
                    Device.device_code
                )
                .select { Device.room_id eq roomId }
                .map { DeviceMap.toDeviceMap(it) }
        }
    }

    override fun checkrole(userId: Int): Role {
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

    override fun roomdevice(id:Int): List<RoomDeviceModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            Room
                .select{Room.agency_id eq id}
                .map { RoomDeviceMap.toRoomDevice(it) }
        }
    }

    override fun problemdetail(): List<ProblemDetailModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            Problem
                .selectAll()
                .map { ProblemDetailMap.toProblemDetailMap(it) }
        }
    }

    override fun devicedetail(deviceId: Int): List<DeviceDetailModel> {
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

    override fun insertRepair(req: InsertRepairRequest): BaseResponse {
        val response = BaseResponse()

        val statement = transaction {
            addLogger(StdOutSqlLogger)

            Repair.insert {
                it[user_id] = req.user_id.toString().toInt()
                it[employee_id]=0
                it[problem_id] = req.problem_id.toString().toInt()
                it[status_id] = 0
                it[repair_date] = DateTime.now()
                it[detail] = req.detail.toString()
                it[device_id]=req.device_id.toString().toInt()
            }

        }
        val result = statement.resultedValues?.size == 1
        response.success = result
        response.message = "Insert success"
        return response
    }
    override fun history(userId: Int): List<History> {
        return transaction {
            addLogger(StdOutSqlLogger)
            (Repair innerJoin Users innerJoin Agency innerJoin Room innerJoin Problem innerJoin Status)
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
     override fun getjob():List<JobModel>{
         return  transaction {
             addLogger(StdOutSqlLogger)
             (Repair innerJoin Problem innerJoin Users innerJoin Agency innerJoin Room)
                 .slice(
                     Repair.repair_date,
                     Agency.agency_name,
                     Room.room_number,
                     Problem.problem_name,
                     Repair.repair_id
                 )
                 .select { Repair.employee_id eq 0 }
                 .map { JobMap.toJob(it) }
         }
     }

    override fun SelectGetJob(repair_id: Int):GetJobModel {

        return  transaction {
            addLogger(StdOutSqlLogger)
            (Repair innerJoin Problem innerJoin Users innerJoin Agency innerJoin Room)
                .slice(
                    Repair.repair_date,
                    Agency.agency_name,
                    Room.room_number,
                    Problem.problem_name,
                )
                .select { Repair.repair_id eq repair_id  }
                .map { JobMap.toGetJob(it) }
                .single()

        }

    }

    override fun Accept(req: AcceptRequest): AcceptResponse {
        val response = AcceptResponse()
        val qq=req.employee_id
        transaction {
            addLogger(StdOutSqlLogger)
            Repair.update({Repair.repair_id eq req.job_id} ){
                it [employee_id]=req.employee_id
            }


        }
        response.success=true
        response.message = "Insert success"
        return response
    }

    override fun checkemployee(jobId: Int): EmployeeModel {
        return transaction {
            addLogger(StdOutSqlLogger)
            Repair
                .slice(Repair.employee_id)
                .select { Repair.repair_id eq jobId  }
                .map { EmployeeMap.toEmployeeMap(it)  }
                .single()
        }
    }

    override fun savejob(req: SaveJogRequest) {
        return transaction {
            addLogger(StdOutSqlLogger)
            Repair.update({Repair.repair_id eq req.repair_job}){
                it[status_id]=req.status_id
                it[test_result]=req.test_result.toString()
            }

        }
    }
    override fun youjob(userId: Int):List<YoujobModel>{
        return  transaction {
            addLogger(StdOutSqlLogger)
            (Repair innerJoin Problem innerJoin Users innerJoin Agency innerJoin Room)
                .slice(
                    Repair.repair_date,
                    Agency.agency_name,
                    Room.room_number,
                    Problem.problem_name,
                    Repair.repair_id
                )
                .select { Repair.employee_id eq userId  }
                .map { JobMap.toyoujob(it) }
        }
    }

}

