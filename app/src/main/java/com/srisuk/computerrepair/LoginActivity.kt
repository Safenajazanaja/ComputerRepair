package com.srisuk.computerrepair

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.srisuk.computerrepair.ui.BaseActivity
import com.srisuk.computerrepair.ui.hideSoftKeyboard
import com.srisuk.computerrepair.data.request.LoginRequest
import com.srisuk.computerrepair.data.response.LoginResponse
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
                val intent = Intent(baseContext, MainActivity::class.java)
                startActivity(intent);

            }else{
                Toast.makeText(baseContext, "ตรวจสอบรหัสอีกครั้ง", Toast.LENGTH_SHORT).show()
            }
        }
        root_Layout.setOnClickListener { hideSoftKeyboard() }
    }

}