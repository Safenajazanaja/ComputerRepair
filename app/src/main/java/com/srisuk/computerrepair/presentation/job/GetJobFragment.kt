package com.srisuk.computerrepair.presentation.job

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_get_job.*

class GetJobFragment : BaseFragment(R.layout.fragment_get_job) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adt = GetJobAdepter()
        recycler_view_job.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = adt
        }
           val data =dataSource.getjob()
        adt.setList(data)
        adt.onClick ={
            val intent = Intent(context, TestResultActivity::class.java).apply {
                val repair_job = it.repair_id
//                Log.d(TAG, "onActivityCreated:$repair_job ")
                putExtra("repair_job",it.repair_id)


            }
            startActivity(intent)
        }
    }
}


