package com.srisuk.computerrepair.presentation.operate

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.request.SaveJogRequest
import com.srisuk.computerrepair.presentation.main.MainActivity
import com.srisuk.computerrepair.presentation.repair.RepairFragment
import com.srisuk.computerrepair.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_test.*
import java.text.SimpleDateFormat
import java.util.*

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
        tv_date_time.text = dateString
        var str = SimpleDateFormat("mm:ss", Locale.getDefault()).format(aa) + "นาที"
        val HH =
            (SimpleDateFormat("HH", Locale.getDefault()).format(aa).toInt().minus(7)).toString()
                .padStart(2, '0')
        str = "$HH:$str"
        tv_count_time.text = str
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
        bt_cancel_you_get.setOnClickListener {
            startActivity(Intent(baseContext, MainActivity::class.java))
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}