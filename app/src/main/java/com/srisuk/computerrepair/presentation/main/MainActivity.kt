package com.srisuk.computerrepair.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.srisuk.computerrepair.GetJobFragment
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.database.Users
import com.srisuk.computerrepair.presentation.repair.RepairFragment
import com.srisuk.computerrepair.presentation.history.HistoryFragment
import com.srisuk.computerrepair.presentation.profile.ProfileFragment
import com.srisuk.computerrepair.toast
import com.srisuk.computerrepair.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId = getSharedPreferences("file", MODE_PRIVATE).getInt("userId",0)
        val role = dataSource.checkrole(userId);
        toast("$role")
        if (role.role == 1) {
            bottom_navigation.setOnNavigationItemSelectedListener(navListener)
            bottom_navigation.visibility = VISIBLE
            bottom_navigation2.visibility = GONE
        } else {
            bottom_navigation2.setOnNavigationItemSelectedListener(navListener)
            bottom_navigation2.visibility = VISIBLE
            bottom_navigation.visibility = GONE
        }

        if (savedInstanceState == null)
            replaceFragment(RepairFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            fragment
        ).commit()
    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.nav_repair -> RepairFragment()
                R.id.nav_job -> GetJobFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> HistoryFragment()
            }
            replaceFragment(selectedFragment)
            true
        }
}
