package com.srisuk.computerrepair.presentation.operate

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.presentation.job.TestResultActivity
import com.srisuk.computerrepair.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_operate.*

class OperateFragment: BaseFragment(R.layout.fragment_operate)  {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userId = context?.getSharedPreferences(
            "file",
            AppCompatActivity.MODE_PRIVATE
        )?.getInt("userId", 0)
        val adt = OperateAdepter()
        recycler_view_youjob.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adt
        }
        val data = userId?.let { dataSource.youjob(it) }
        adt.setList(data)
        adt.onClick = {
            val intent = Intent(context, TestActivity::class.java).apply {
//                val repair_job = it.repair_id
//                Log.d(TAG, "onActivityCreated:$repair_job ")
                putExtra("repair_job1", it.repair_id)



            }
            startActivity(intent)
        }
    }
}