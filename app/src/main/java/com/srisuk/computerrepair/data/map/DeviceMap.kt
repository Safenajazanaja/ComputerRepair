package com.srisuk.computerrepair.data.map

import com.srisuk.computerrepair.data.database.Device
import com.srisuk.computerrepair.data.database.DeviceDetail
import com.srisuk.computerrepair.data.models.DeviceModel
import org.jetbrains.exposed.sql.ResultRow

object DeviceMap {
    fun toDeviceMap(row: ResultRow)=DeviceModel(
        device_id = row[Device.device_id],
        device_code = row[Device.device_code],
    )
}