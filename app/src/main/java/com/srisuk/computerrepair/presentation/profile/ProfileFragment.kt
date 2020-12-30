package com.srisuk.computerrepair.presentation.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.ui.BaseFragment
import kotlinx.android.synthetic.main.item_profile.*

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val profile = dataSource.profile(1)


        tv_member.text = profile.userId.toString()
        tv_full_name.text = profile.name
        tv_agency.text = profile.agency_name
        tv_phone.text = profile.telephone
    }

}
