package com.srisuk.computerrepair.presentation.repair

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.DeviceDetailModel
import com.srisuk.computerrepair.data.models.DeviceModel
import com.srisuk.computerrepair.data.models.ProblemDetailModel
import com.srisuk.computerrepair.data.models.RoomDeviceModel
import com.srisuk.computerrepair.data.request.InsertRepairRequest
import com.srisuk.computerrepair.data.request.LoginRequest
import com.srisuk.computerrepair.ui.BaseFragment
import com.srisuk.computerrepair.ui.onItemSelected
import kotlinx.android.synthetic.main.fragment_repair.*


class RepairFragment : BaseFragment(R.layout.fragment_repair) {
    private lateinit var room: RoomDeviceModel
    private lateinit var device: DeviceModel
    private lateinit var devicename:DeviceDetailModel
    private lateinit var problem:ProblemDetailModel
    private var problemId: Int? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userId =context?.getSharedPreferences("file",
            AppCompatActivity.MODE_PRIVATE)?.getInt("userId",0)
        setSpinnerAddressRoom()
        setSpinnerProblem()
        val agency = userId?.let { dataSource.checkagency(it) }
        tv_agency_name.text=agency?.agency_name
        btn_repair.setOnClickListener {
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "onActivityCreated: $problemId")
//            val problem_id =problem.problemId
//            val roomId = room.roomId
//            val device_code =device.device_id
//            val detail = edt_detail.text.toString()
//            val req =InsertRepairRequest()

        }

    }
    private fun setSpinnerProblem(){
        val list = dataSource.problemdetail() as MutableList<ProblemDetailModel>
        bar_spinner_problem.adapter=ProblemAdapter(requireContext(),list)
        bar_spinner_problem.onItemSelected<ProblemDetailModel>{
            problem =it
            problemId = it.problemId

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

