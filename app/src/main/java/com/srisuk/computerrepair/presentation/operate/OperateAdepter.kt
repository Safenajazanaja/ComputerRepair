package com.srisuk.computerrepair.presentation.operate

import android.view.View
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.YoujobModel
import com.srisuk.computerrepair.ui.BaseRecyclerView

import kotlinx.android.synthetic.main.item_youjob.view.*
import java.text.SimpleDateFormat
import java.util.*

class OperateAdepter: BaseRecyclerView<YoujobModel>() {
    override fun getLayout(): Int = R.layout.item_youjob

    override fun View.onBindViewHolder(data: YoujobModel) {

        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateTimeStr = sdf.format( data.date_job)
        tv_dete_youjob.text= dateTimeStr
        tv_agency_youjob.text=data.agency_job
        tv_room_youjob.text=data.room_job
        tv_problem_youjob.text=data.problem_job
    }
}