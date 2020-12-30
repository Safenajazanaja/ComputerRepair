package com.srisuk.computerrepair.presentation.repair

import android.os.Bundle
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.DeviceDetailModel
import com.srisuk.computerrepair.data.models.DeviceModel
import com.srisuk.computerrepair.data.models.ProblemDetailModel
import com.srisuk.computerrepair.data.models.RoomDeviceModel
import com.srisuk.computerrepair.ui.BaseFragment
import com.srisuk.computerrepair.ui.onItemSelected
import kotlinx.android.synthetic.main.fragment_repair.*


class RepairFragment : BaseFragment(R.layout.fragment_repair) {
    private lateinit var room: RoomDeviceModel
    private lateinit var device: DeviceModel
    private lateinit var devicename:DeviceDetailModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSpinnerAddressRoom()
        val adt = ProblemAdapter(requireContext(), dataSource.problemdetail() as MutableList<ProblemDetailModel>)
        bar_spinner_problem.apply {
            adapter = adt
        }
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
            it.device_id?.let { it1 -> setSpinnerAddressDeviceName(it1) }
        }
    }
    private fun setSpinnerAddressDeviceName(deviceId:Int) {
        val list = dataSource.devicedetail(deviceId) as MutableList<DeviceDetailModel>
        bar_spinner_device_name.adapter = DeviceNameAdapter(
            requireContext(), list
        )
        bar_spinner_device_name.onItemSelected<DeviceDetailModel> {
            devicename = it
        }
    }
}

