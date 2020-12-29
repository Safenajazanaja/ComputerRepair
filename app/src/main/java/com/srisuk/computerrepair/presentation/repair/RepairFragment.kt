package com.srisuk.computerrepair.presentation.repair

import android.os.Bundle
import android.widget.Spinner
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.database.Device
import com.srisuk.computerrepair.data.models.DeviceModel
import com.srisuk.computerrepair.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_repair.*


class RepairFragment : BaseFragment(R.layout.fragment_repair) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        val adt = DeviceAdapter(ba, dataSource as MutableList<DeviceModel>)
        bar_spinner_device.apply {
            val device =dataSource.devices(1)

        }
        val data = dataSource.devices(1)



    }
}

