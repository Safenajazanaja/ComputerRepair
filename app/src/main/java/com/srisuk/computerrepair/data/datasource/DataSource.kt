package com.srisuk.computerrepair.data.datasource

import com.srisuk.computerrepair.data.models.*
import com.srisuk.computerrepair.data.request.AcceptRequest
import com.srisuk.computerrepair.data.request.InsertRepairRequest
import com.srisuk.computerrepair.data.request.LoginRequest
import com.srisuk.computerrepair.data.request.SaveJogRequest
import com.srisuk.computerrepair.data.response.AcceptResponse
import com.srisuk.computerrepair.data.response.BaseResponse
import com.srisuk.computerrepair.data.response.EmployeeResponse
import com.srisuk.computerrepair.data.response.LoginResponse
import org.joda.time.DateTime

interface DataSource {
    fun login(req: LoginRequest): LoginResponse
    fun profile(userId: Int): Profile
    fun history(userId: Int,st:Int,date: DateTime): List<History>
    fun devices(roomId: Int): List<DeviceModel>
    fun checkrole(userId: Int): Role
    fun roomdevice(id:Int): List<RoomDeviceModel>
    fun problemdetail(): List<ProblemDetailModel>
    fun devicedetail(deviceId:Int):List<DeviceDetailModel>
    fun checkagency(userId: Int):AgencyNameModel
    fun insertRepair(req: InsertRepairRequest): BaseResponse
    fun getjob():List<JobModel>
    fun SelectGetJob(repair_id: Int):GetJobModel
    fun Accept (req: AcceptRequest):AcceptResponse
    fun checkemployee(jobId:Int):EmployeeModel
    fun savejob(req: SaveJogRequest)
    fun youjob(userId: Int):List<YoujobModel>
    fun historyall(userId: Int,date:DateTime): List<History>
}