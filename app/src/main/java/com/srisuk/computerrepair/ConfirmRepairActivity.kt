package com.srisuk.computerrepair

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.srisuk.computerrepair.data.request.InsertRepairRequest
import com.srisuk.computerrepair.presentation.main.MainActivity
import com.srisuk.computerrepair.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_confirm_repair.*
import org.joda.time.DateTime

class ConfirmRepairActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_repair)
        val detail = intent.getStringExtra("detail")
        val code = intent.getStringExtra("code")
        val problemName = intent.getStringExtra("problemName")
        val roomnumber = intent.getStringExtra("roomnumber")
        val namecode= intent.getStringExtra("namecode")
        val user_id = intent.getIntExtra("user_id", 0)
        val employee_id = intent.getIntExtra("employee_id", 0)
        val problem_id = intent.getIntExtra("problem_id", 0)
        val status_id = intent.getIntExtra("status_id", 0)
        val device_id = intent.getIntExtra("device_id", 0)
        tv_detail_confirm.text=problemName.toString()
        tv_code_confirm.text=code.toString()
        tv_number_confirm.text=roomnumber.toString()
        tv_name_code_confirm.text=namecode.toString()
        val agency=dataSource.checkagency(user_id)
        tv_agency_name_confirm.text=agency.agency_name.toString()
        tv_code_confirm.text= "รายละเอียดเพิ่มเติม:$detail"
        bt_cancel_confirm.setOnClickListener {
            startActivity(Intent(baseContext, MainActivity::class.java))
        }
        bt_confirm_confirm.setOnClickListener {
            val req = InsertRepairRequest(
                user_id, employee_id, problem_id, status_id, DateTime.now(), detail.toString(), null, device_id
            )
            val result = dataSource.insertRepair(req)
            if (result.success) {
                Toast.makeText(baseContext, result.message, Toast.LENGTH_SHORT).show()
                startActivity(Intent(baseContext, MainActivity::class.java))
            } else {
                Toast.makeText(baseContext, result.message, Toast.LENGTH_SHORT).show()
            }
        }

    }
}