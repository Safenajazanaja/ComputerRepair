package com.srisuk.computerrepair.presentation.job

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.request.AcceptRequest
import com.srisuk.computerrepair.data.request.SaveJogRequest
import com.srisuk.computerrepair.presentation.main.MainActivity
import com.srisuk.computerrepair.presentation.repair.RepairFragment
import com.srisuk.computerrepair.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_testressult.*
import java.text.SimpleDateFormat
import java.util.*

class TestResultActivity : BaseActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var selectedRadioButton: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testressult)
        val repair_job = intent.getIntExtra("repair_job", 0)
        val userId = getSharedPreferences("file", MODE_PRIVATE).getInt("userId", 0)

//        Log.d(ContentValues.TAG, "onActivityCreated:$repair_job ")

        //radioGroup
        radioGroup = findViewById(R.id.radio_grou)


        val data = dataSource.SelectGetJob(repair_job)
        val chak = dataSource.checkemployee(repair_job)
//        Log.d(ContentValues.TAG, "onActivityCreated1:$userId ")

        if (chak.id_emp == 0) {
            Bt_getjob.visibility = View.VISIBLE

        } else {
            Bt_getjob.visibility = View.GONE

        }
        bt_cancel_get_job.setOnClickListener {
            startActivity(Intent(baseContext, MainActivity::class.java))
        }
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateTimeStr = sdf.format( data.date_job)
        tv_room_get.text = data.room_job.toString()
        tv_detail_get.text = data.problem_job.toString()
        tv_dete_get.text = dateTimeStr

//        Bt_getjob.setOn
//        Bt_getjob.visibility = View.GONE
        Bt_getjob.setOnClickListener {
            val req = AcceptRequest(userId, repair_job)
            val dataaccept = dataSource.Accept(req)
//           Log.d(ContentValues.TAG, "onActivityCreated2:$dataaccept ")
            if (dataaccept.success == true) {
                startActivity(Intent(baseContext, MainActivity::class.java))
            }


        }
        val test_result = til_test_result_get.text.toString().trim()
//        Bt_ok_get.setOnClickListener {
//
//            val selectedRadioButtonId: Int = radioGroup.checkedRadioButtonId
//            selectedRadioButton=findViewById(selectedRadioButtonId)
//            if (selectedRadioButtonId==2131231013 ){
//                val datesavejob= SaveJogRequest(1,test_result,repair_job)
//                dataSource.savejob(datesavejob)
//                startActivity(Intent(baseContext, RepairFragment::class.java))
//            }else if (selectedRadioButtonId==2131231014){
//                val datesavejob= SaveJogRequest(2,test_result,repair_job)
//                dataSource.savejob(datesavejob)
//                startActivity(Intent(baseContext, RepairFragment::class.java))
//            }
//            Log.d(ContentValues.TAG, "onActivityCreated2:$selectedRadioButtonId ")
//
//
//        }


    }
}

