package com.srisuk.computerrepair.data.datasource

import com.srisuk.computerrepair.data.models.*
import com.srisuk.computerrepair.data.request.AcceptRequest
import com.srisuk.computerrepair.data.request.InsertRepairRequest
import com.srisuk.computerrepair.data.request.LoginRequest
import com.srisuk.computerrepair.data.response.AcceptResponse
import com.srisuk.computerrepair.data.response.BaseResponse
import com.srisuk.computerrepair.data.response.EmployeeResponse
import com.srisuk.computerrepair.data.response.LoginResponse

interface DataSource {
    fun login(req: LoginRequest): LoginResponse
    fun profile(userId: Int): Profile
    fun history(userId: Int): List<History>
    fun devices(roomId: Int): List<DeviceModel>
    fun checkrole(userId: Int): Role
    fun roomdevice(): List<RoomDeviceModel>
    fun problemdetail(): List<ProblemDetailModel>
    fun devicedetail(deviceId:Int):List<DeviceDetailModel>
    fun checkagency(userId: Int):AgencyNameModel
    fun insertRepair(req: InsertRepairRequest): BaseResponse
    fun getjob():List<JobModel>
    fun SelectGetJob(repair_id: Int):GetJobModel
    fun Accept (req: AcceptRequest):AcceptResponse
    fun checkemployee(jobId:Int):EmployeeModel
}