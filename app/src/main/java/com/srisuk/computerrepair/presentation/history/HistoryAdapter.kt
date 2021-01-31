package com.srisuk.computerrepair.presentation.history

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.History
import com.srisuk.computerrepair.ui.BaseRecyclerView
import kotlinx.android.synthetic.main.item_history.view.*
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter : BaseRecyclerView<History>() {

    override fun getLayout(): Int = R.layout.item_history

    override fun View.onBindViewHolder(data: History) {

        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateTimeStr = sdf.format(data.Date)

        tv_dete.text = dateTimeStr
        Log.d(TAG, "onBindViewHolder:$dateTimeStr")
        tv_agency.text = data.Agency.toString()
        tv_room.text = data.Room.toString()
        tv_detail.text = data.Problem.toString()
        tv_name.text = data.Status.toString()
    }
}
