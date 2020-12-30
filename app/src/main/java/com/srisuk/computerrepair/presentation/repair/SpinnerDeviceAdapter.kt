package com.srisuk.computerrepair.presentation.repair

import android.content.Context
import android.view.View
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.models.DeviceDetailModel
import com.srisuk.computerrepair.data.models.DeviceModel
import com.srisuk.computerrepair.data.models.ProblemDetailModel
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
class ProblemAdapter(
    context: Context,
    list: MutableList<ProblemDetailModel>
) : BaseSpinner<ProblemDetailModel>(context, list) {
    override fun getLayout(): Int = R.layout.item_spinner_base
    override fun View.onBindViewHolder(data: ProblemDetailModel) {
        tvSpinnerBase.text = data.problem_name
    }

}
class DeviceNameAdapter(
    context: Context,
    list: MutableList<DeviceDetailModel>
) : BaseSpinner<DeviceDetailModel>(context, list) {
    override fun getLayout(): Int = R.layout.item_spinner_base
    override fun View.onBindViewHolder(data: DeviceDetailModel) {
        tvSpinnerBase.text = data.device_detail_name
    }

}


