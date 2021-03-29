package com.srisuk.computerrepair.presentation.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.presentation.login.LoginActivity
import com.srisuk.computerrepair.ui.BaseFragment
import com.srisuk.computerrepair.ui.BaseRecyclerView
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_profile.*
import kotlinx.android.synthetic.main.item_profile.tv_agency
import kotlinx.android.synthetic.main.item_profile.tv_full_name
import kotlinx.android.synthetic.main.item_profile.tv_member
import kotlinx.android.synthetic.main.item_profile.tv_phone

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var userId =context?.getSharedPreferences("file",
            AppCompatActivity.MODE_PRIVATE)?.getInt("userId",0)
        val profile = userId?.let { dataSource.profile(it) }
        tv_member.text = profile?.userId.toString()
        tv_full_name.text = profile?.name
        tv_agency.text = profile?.agency_name
        tv_phone.text = profile?.telephone
//        im_logout.setOnClickListener {
//            var ed=context?.getSharedPreferences("file",AppCompatActivity.MODE_PRIVATE)
//            ed?.edit()?.remove("userId")?.commit()
//            var intent = Intent(context, LoginActivity::class.java)
//            startActivity(intent)
//            activity?.finish()
//        }
    }

}
