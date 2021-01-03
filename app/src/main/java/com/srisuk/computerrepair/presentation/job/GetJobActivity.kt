package com.srisuk.computerrepair.presentation.job

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.srisuk.computerrepair.R

class GetJobActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)
        val  repair_job= intent.getIntExtra("repair_job", 0)

        Log.d(ContentValues.TAG, "onActivityCreated:$repair_job ")
    }
}