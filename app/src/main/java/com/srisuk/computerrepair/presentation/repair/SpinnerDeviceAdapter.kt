package com.srisuk.computerrepair.presentation.repair

import android.content.Context
import android.view.View
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.DeviceModel
import com.srisuk.computerrepair.ui.BaseSpinner
import kotlinx.android.synthetic.main.item_spinner_base.view.*


class DeviceAdapter(
    context: Context,
    list: MutableList<DeviceModel>
) : BaseSpinner<DeviceModel>(context, list) {
    override fun getLayout(): Int = R.layout.item_spinner_base
    override fun View.onBindViewHolder(data: DeviceModel) {
        tvSpinnerBase.text = data.device_code
    }


}

