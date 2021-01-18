package com.srisuk.computerrepair.presentation.operate

import android.view.View
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.YoujobModel
import com.srisuk.computerrepair.ui.BaseRecyclerView

import kotlinx.android.synthetic.main.item_youjob.view.*

class OperateAdepter: BaseRecyclerView<YoujobModel>() {
    override fun getLayout(): Int = R.layout.item_youjob

    override fun View.onBindViewHolder(data: YoujobModel) {

        tv_dete_youjob.text= data.date_job?.toString("dd-MM-yyyy")
        tv_agency_youjob.text=data.agency_job
        tv_room_youjob.text=data.room_job
        tv_problem_youjob.text=data.problem_job
    }
}