package com.srisuk.computerrepair.presentation.history

import android.content.ContentValues
import android.util.Log
import android.view.View
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.History
import com.srisuk.computerrepair.ui.BaseRecyclerView
import kotlinx.android.synthetic.main.item_history.view.*
import java.text.SimpleDateFormat

class HistoryAdapter : BaseRecyclerView<History>() {

    override fun getLayout(): Int = R.layout.item_history

    override fun View.onBindViewHolder(data: History) {

        tv_dete.text = data.Date?.toString("dd-MM-yyyy")
        tv_agency.text = data.Agency.toString()
        tv_room.text = data.Room.toString()
        tv_detail.text = data.Problem.toString()
        tv_name.text = data.Status.toString()
//        Log.d(ContentValues.TAG, "onActivityCreated2:$sumdate ")
        if (data.End==null){
            enddate.text="--"
        }else{
            val startTime = data.End
            val sdf = SimpleDateFormat("dd-MM-YYYY")
            val startTimeStr = sdf.format(startTime)
            val sdf2 = SimpleDateFormat("HH:mm:ss")
            val startTimeStr2 = sdf2.format(startTime)
            Log.d(ContentValues.TAG, "onActivityCreated2:$startTime ")
            val sum=data.End- data.Star!!
            enddate.text= startTimeStr.toString()
            endtime.text=startTimeStr2.toString()

//            Log.d(ContentValues.TAG, "onActivityCreated2:$sum ")
        }
    }
}
