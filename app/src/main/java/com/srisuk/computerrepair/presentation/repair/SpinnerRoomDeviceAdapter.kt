package com.srisuk.computerrepair.presentation.repair

import android.content.Context
import android.view.View
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.RoomDeviceModel
import com.srisuk.computerrepair.ui.BaseSpinner
import kotlinx.android.synthetic.main.item_spinner_base.view.*

class RoomDeviceAdapter(
    context: Context,
    list: MutableList<RoomDeviceModel>
) : BaseSpinner<RoomDeviceModel>(context, list) {
    override fun getLayout(): Int = R.layout.item_spinner_base
    override fun View.onBindViewHolder(data: RoomDeviceModel) {
        tvSpinnerBase.text = data.roomNumber
    }
}