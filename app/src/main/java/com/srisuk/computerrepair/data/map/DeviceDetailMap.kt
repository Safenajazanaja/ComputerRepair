package com.srisuk.computerrepair.data.map

import com.srisuk.computerrepair.data.database.DeviceDetail
import com.srisuk.computerrepair.data.models.DeviceDetailModel
import org.jetbrains.exposed.sql.ResultRow

object DeviceDetailMap {
    fun toDeviceDetail(row: ResultRow)=DeviceDetailModel(
    device_detail_id  =row[DeviceDetail.device_detail_id],
    device_detail_name=row[DeviceDetail.device_detail_name]
    )
}