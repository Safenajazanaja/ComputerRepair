package com.srisuk.computerrepair.presentation.repair

import android.os.Bundle
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.DeviceModel
import com.srisuk.computerrepair.data.models.RoomDeviceModel
import com.srisuk.computerrepair.ui.BaseFragment
import com.srisuk.computerrepair.ui.onItemSelected
import kotlinx.android.synthetic.main.fragment_repair.*


class RepairFragment : BaseFragment(R.layout.fragment_repair) {
    private lateinit var room: RoomDeviceModel
    private lateinit var device: DeviceModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSpinnerAddressRoom()
//        val adt = RoomDeviceAdapter(requireContext(), dataSource.devices() as MutableList<DeviceModel>)
//        bar_spinner_room.apply {
//            adapter = adt
//        }
    }

    private fun setSpinnerAddressRoom() {
        val list = dataSource.roomdevice() as MutableList<RoomDeviceModel>
        bar_spinner_room.adapter = RoomDeviceAdapter(
            requireContext(), list)
        bar_spinner_room.onItemSelected<RoomDeviceModel> {
            room = it
            it.roomId?.let { it1 -> setSpinnerAddressDevice(it1) }
        }
    }

    private fun setSpinnerAddressDevice(roomId: Int) {
        val list = dataSource.devices(roomId) as MutableList<DeviceModel>
        bar_spinner_device.adapter = DeviceAdapter(
            requireContext(),list)
        bar_spinner_device.onItemSelected<DeviceModel> {
            device = it
        }

    }
}

