package com.srisuk.computerrepair.presentation.operate

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TimeUtils
import android.widget.RadioButton
import android.widget.RadioGroup
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.request.SaveJogRequest
import com.srisuk.computerrepair.presentation.repair.RepairFragment
import com.srisuk.computerrepair.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.activity_testressult.*
import org.joda.time.DateTime
import java.text.SimpleDateFormat
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit

class TestActivity : BaseActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var selectedRadioButton: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val repair_job = intent.getIntExtra("repair_job1", 0)
        val data = dataSource.SelectGetJob(repair_job)
        radioGroup = findViewById(R.id.radio_groupyou)
       val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
       val dateString = simpleDateFormat.format(data.count_time)
        val datetimeOperate = Calendar.getInstance().timeInMillis
        val daetlong = data.count_time
        val aa = datetimeOperate - daetlong
        tv_room_youget.text = data.room_job.toString()
        tv_detail_youget.text = data.problem_job.toString()
        tv_dete_youget.text = data.date_job?.toString("dd-MM-yyyy")
        tv_date_time.text =dateString
        tv_count_time.text=TimeUnit.MILLISECONDS.toHours(aa).toString()+"ชั่วโมง"
                Log.d(TAG, "onCreate:$aa ")

        Bt_ok_youget.setOnClickListener {

            val selectedRadioButtonId: Int = radio_groupyou.checkedRadioButtonId
            selectedRadioButton = findViewById(selectedRadioButtonId)
            if (selectedRadioButtonId == 2131231017) {
                val test_result = til_test_result_youget.text.toString().trim()
                val datesavejob = SaveJogRequest(1, test_result, repair_job)
                dataSource.savejob(datesavejob)
                startActivity(Intent(baseContext, RepairFragment::class.java))
            } else if (selectedRadioButtonId == 2131231019) {
                val test_result = til_test_result_youget.text.toString().trim()
                val datesavejob = SaveJogRequest(2, test_result, repair_job)
                dataSource.savejob(datesavejob)
                startActivity(Intent(baseContext, RepairFragment::class.java))
            }
            Log.d(ContentValues.TAG, "onActivityCreated2:$til_test_result_youget ")


        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}