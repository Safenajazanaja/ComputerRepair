package com.srisuk.computerrepair.presentation.job

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.GetJobModel
import com.srisuk.computerrepair.data.models.JobModel
import com.srisuk.computerrepair.presentation.main.MainActivity
import com.srisuk.computerrepair.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_testressult.*

class TestResultActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testressult)
        val  repair_job= intent.getIntExtra("repair_job",1)

        Log.d(ContentValues.TAG, "onActivityCreated:$repair_job ")


         val data = dataSource.SelectGetJob(repair_job)


        tv_room_get.text= data.room_job.toString()
        tv_detail_get.text=data.problem_job.toString()
        tv_dete_get.text=data.date_job?.toString("dd-MM-yyyy")


    }



}
