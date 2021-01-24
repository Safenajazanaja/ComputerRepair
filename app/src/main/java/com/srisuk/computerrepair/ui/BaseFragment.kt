package com.srisuk.computerrepair.ui

import android.os.Bundle
import android.os.StrictMode
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.srisuk.computerrepair.data.datasource.DataSource
import com.srisuk.computerrepair.data.datasource.DataSourceImpl
import org.jetbrains.exposed.sql.Database

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    val dataSource: DataSource = DataSourceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId =context?.getSharedPreferences("file", AppCompatActivity.MODE_PRIVATE)?.getInt("userId",0)
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        val host = "192.168.0.197"
        val databaseName = "repairdb"
        val url = "jdbc:mysql://$host:3306/$databaseName?useUnicode=true&characterEncoding=utf-8"
        Database.connect(
            url = url,
            driver = "com.mysql.jdbc.Driver",
            user = "computerrepair",
            password = "1234",
        )
    }
}
