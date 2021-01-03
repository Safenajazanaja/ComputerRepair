package com.srisuk.computerrepair.presentation.job

import android.view.View
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.GetJobModel
import com.srisuk.computerrepair.ui.BaseRecyclerView
import kotlinx.android.synthetic.main.item_job.view.*

class GetJobAdepter:BaseRecyclerView<GetJobModel>() {
    override fun getLayout(): Int = R.layout.item_job

    override fun View.onBindViewHolder(data: GetJobModel) {

//        tv_dete.text = data.Date?.toString("dd-MM-yyyy")
//        tv_agency.text = data.Agency.toString()
//        tv_room.text = data.Room.toString()
//        tv_detail.text = data.Problem.toString()
//        tv_name.text = data.Status.toString()

        tv_dete_job.text= data.date_job?.toString("dd-MM-yyyy")
        tv_agency_job.text=data.agency_job
        tv_room_job.text=data.room_job
        tv_problem_job.text=data.problem_job
    }
}