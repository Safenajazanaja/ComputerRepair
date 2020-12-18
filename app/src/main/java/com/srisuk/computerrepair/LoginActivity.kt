package com.srisuk.computerrepair

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.srisuk.computerrepair.ui.hideSoftKeyboard
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        root_Layout.setOnClickListener { hideSoftKeyboard() }
    }
}