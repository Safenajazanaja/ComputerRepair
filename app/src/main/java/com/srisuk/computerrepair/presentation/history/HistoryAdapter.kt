package com.srisuk.computerrepair.presentation.history

import android.view.View
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.History
import com.srisuk.computerrepair.ui.BaseRecyclerView
import kotlinx.android.synthetic.main.item.view.*

class HistoryAdapter : BaseRecyclerView<History>() {

    override fun getLayout(): Int = R.layout.item

    override fun View.onBindViewHolder(data: History) {

        tv_dete.text = data.Date?.toString("yyyy-MM-dd")
        tv_agency.text = data.Agency.toString()
        tv_room.text = data.Room.toString()
        tv_detail.text = data.Problem.toString()
        tv_name.text = data.Status.toString()
    }
}

//"yyyy-MM-dd"