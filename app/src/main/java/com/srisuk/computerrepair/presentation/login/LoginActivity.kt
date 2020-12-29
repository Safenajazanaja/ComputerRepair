package com.srisuk.computerrepair.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.ui.BaseActivity
import com.srisuk.computerrepair.ui.hideSoftKeyboard
import com.srisuk.computerrepair.data.request.LoginRequest
import com.srisuk.computerrepair.presentation.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val req = LoginRequest(username, password)
            val result = dataSource.login(req)
            if (result.success){
                // TODO: 12/10/2020 goto main
                val preferences = getSharedPreferences("file", Context.MODE_PRIVATE)
                result.userId?.let { it1 ->
                    preferences.edit()
                        .putInt("userId", it1)
                        .apply()
                }
                val intent = Intent(baseContext, MainActivity::class.java)
                startActivity(intent);

            }else{
                Toast.makeText(baseContext, "ตรวจสอบรหัสอีกครั้ง", Toast.LENGTH_SHORT).show()
            }
        }
        root_Layout.setOnClickListener { hideSoftKeyboard() }

        val data = dataSource.profile(1)
        Log.d(TAG, "onCreate: $data")
    }

    companion object{
        private const val TAG = "LoginActivity"
    }

}