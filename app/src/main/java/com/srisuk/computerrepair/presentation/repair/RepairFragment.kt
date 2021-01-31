package com.srisuk.computerrepair.presentation.repair


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.srisuk.computerrepair.ConfirmRepairActivity
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
import org.joda.time.DateTime

class RepairFragment : BaseFragment(R.layout.fragment_repair) {
    private lateinit var room: RoomDeviceModel
    private lateinit var device: DeviceModel
    private lateinit var devicename:DeviceDetailModel
    private lateinit var problem:ProblemDetailModel
    private var problemName:String?=null
    var req: InsertRepairRequest? = null
    private var code:String?=null
    private var roomnumber:String?=null
    private var namecode:String?=null
    private var problemId: Int? = null
    private var roomId: Int? = null
    private var deviceId: Int? = null
    private var detail : String? =null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userId =context?.getSharedPreferences("file",
            AppCompatActivity.MODE_PRIVATE)?.getInt("userId",0)
        val agency = userId?.let { dataSource.checkagency(it) }
        tv_agency_name.text=agency?.agency_name.toString().trim()
        val agencyId=agency?.agency_id
        agencyId?.let { setSpinnerAddressRoom(it) }
        setSpinnerProblem()
        req =InsertRepairRequest(user_id = userId,employee_id = null,problem_id = problemId,status_id = 2
            ,repair_date = System.currentTimeMillis(),detail,test_result = null, device_id =  deviceId)
        btn_repair.setOnClickListener {
            val intent = Intent(context, ConfirmRepairActivity::class.java).apply {
                putExtra("user_id",req?.user_id)
                putExtra("employee_id",req?.employee_id)
                putExtra("problem_id",problemId)
                putExtra("status_id",2)
                putExtra("repair_date",System.currentTimeMillis())
                putExtra("detail",edt_detail.text.toString().trim())
                putExtra("device_id",deviceId)
                putExtra("problemName",problemName)
                putExtra("code",code)
                putExtra("roomnumber",roomnumber)
                putExtra("namecode",namecode)
            }
            startActivity(intent)
        }

    }
    private fun setSpinnerProblem(){
        val list = dataSource.problemdetail() as MutableList<ProblemDetailModel>
        bar_spinner_problem.adapter=ProblemAdapter(requireContext(),list)
        bar_spinner_problem.onItemSelected<ProblemDetailModel>{
            problem =it
            problemId = it.problemId
            problemName=it.problem_name

        }
    }
    private fun setSpinnerAddressRoom(agency_id:Int) {

        val list = dataSource.roomdevice(agency_id) as MutableList<RoomDeviceModel>
        bar_spinner_room.adapter = RoomDeviceAdapter(
            requireContext(), list)
        bar_spinner_room.onItemSelected<RoomDeviceModel> {
            room = it
            roomId=it.roomId
            roomnumber=it.roomNumber
            roomId?.let { it1 -> setSpinnerAddressDevice(it1) }
        }
    }

    private fun setSpinnerAddressDevice(roomId: Int) {
        val list = dataSource.devices(roomId) as MutableList<DeviceModel>
        bar_spinner_device.adapter = DeviceAdapter(
            requireContext(),list)
        bar_spinner_device.onItemSelected<DeviceModel> {
            device = it
            deviceId= it.device_id
            code=it.device_code
            deviceId?.let { it1 -> setSpinnerAddressDeviceName(it1) }
        }
    }
    private fun setSpinnerAddressDeviceName(deviceId:Int) {
        val list = dataSource.devicedetail(deviceId) as MutableList<DeviceDetailModel>
        bar_spinner_device_name.adapter = DeviceNameAdapter(
            requireContext(), list
        )
        bar_spinner_device_name.onItemSelected<DeviceDetailModel> {
            devicename = it
            namecode =it.device_detail_name

//            bar_spinner_device_name.isEnabled = false
        }
    }

}

