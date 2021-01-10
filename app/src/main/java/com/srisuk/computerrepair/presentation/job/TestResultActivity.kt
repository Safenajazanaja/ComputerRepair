package com.srisuk.computerrepair.presentation.job

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.GetJobModel
import com.srisuk.computerrepair.data.models.JobModel
import com.srisuk.computerrepair.data.request.AcceptRequest
import com.srisuk.computerrepair.data.response.AcceptResponse
import com.srisuk.computerrepair.presentation.main.MainActivity
import com.srisuk.computerrepair.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_testressult.*

class TestResultActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testressult)
        val  repair_job= intent.getIntExtra("repair_job",0)
        val userId = getSharedPreferences("file", MODE_PRIVATE).getInt("userId",0)

//        Log.d(ContentValues.TAG, "onActivityCreated:$repair_job ")


        val data = dataSource.SelectGetJob(repair_job)
        val chak=dataSource.checkemployee(repair_job)
//        Log.d(ContentValues.TAG, "onActivityCreated1:$userId ")

        if (chak.id_emp == 0){
            Bt_getjob.visibility=View.VISIBLE

        }else{
            Bt_getjob.visibility = View.GONE

        }

        tv_room_get.text= data.room_job.toString()
        tv_detail_get.text=data.problem_job.toString()
        tv_dete_get.text=data.date_job?.toString("dd-MM-yyyy")

//        Bt_getjob.setOn
//        Bt_getjob.visibility = View.GONE
        Bt_getjob.setOnClickListener {
            val req= AcceptRequest(userId,repair_job)
            val dataaccept =dataSource.Accept(req)
//           Log.d(ContentValues.TAG, "onActivityCreated2:$dataaccept ")
            if (dataaccept.success==true){
                startActivity(Intent(baseContext, MainActivity::class.java))
            }


        }





    }



}
