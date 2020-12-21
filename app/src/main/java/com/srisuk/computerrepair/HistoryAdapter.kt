package com.srisuk.computerrepair

import android.view.View
import com.srisuk.computerrepair.data.models.History
import com.srisuk.computerrepair.ui.BaseRecyclerView
import kotlinx.android.synthetic.main.item.view.*

class HistoryAdapter : BaseRecyclerView<History>() {

    override fun getLayout(): Int = R.layout.item

    override fun View.onBindViewHolder(data: History) {
        tv_dete.text = data.datejob.toString()
    }

}
