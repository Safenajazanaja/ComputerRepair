package com.srisuk.computerrepair.data.map

import com.srisuk.computerrepair.data.database.Room
import com.srisuk.computerrepair.data.models.RoomDeviceModel
import org.jetbrains.exposed.sql.ResultRow

object RoomDeviceMap {
    fun toRoomDevice(row: ResultRow) = RoomDeviceModel(
        roomId = row[Room.room_id],
        roomNumber =row[Room.room_number]
    )
}