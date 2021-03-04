package com.srisuk.computerrepair.presentation.history

import android.app.DatePickerDialog
import android.content.ContentValues
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.datasource.DataSourceImpl
import com.srisuk.computerrepair.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_history.*
import org.joda.time.DateTime
import java.text.SimpleDateFormat


class HistoryFragment : BaseFragment(R.layout.fragment_history) {
    private var mCalendar: Calendar? = null
    private lateinit var selectedRadioButtonhis: RadioButton

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userId = context?.getSharedPreferences(
            "file",
            AppCompatActivity.MODE_PRIVATE
        )?.getInt("userId", 0)

        val adt = HistoryAdapter()
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adt
        }



        bt_okhis.setOnClickListener {
            val date = DateTime(mCalendar?.timeInMillis)
            var id: Int = radio_groujob.checkedRadioButtonId
            Log.d(ContentValues.TAG, "onActivityCreated3:$id ")
            if (id == 2131231022) {
                val data = userId?.let { it1 -> dataSource.history(userId = it1,st = 1,date = date ) }
                adt.setList(data)
            } else if (id==2131231023) {
                val data= userId?.let { it1 -> dataSource.historyall(userId = it1,date = date) }
                adt.setList(data)
                Toast.makeText(
                    context,
                    "${id}",
                    Toast.LENGTH_SHORT
                ).show()
            }



        }



        bt_dete.setOnClickListener {
            val calendar = mCalendar ?: Calendar.getInstance()

            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val day = calendar[Calendar.DAY_OF_MONTH]

            calendar.add(Calendar.DATE, 0)
            val dateDialog = DatePickerDialog(
                requireContext(),
                { view, year, month, dayOfMonth ->
                    Toast.makeText(
                        context,
                        "$year/${month.plus(1)}/$dayOfMonth",
                        Toast.LENGTH_SHORT
                    ).show()

                    val calendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
                    mCalendar = calendar
                    bt_okhis.isEnabled=true

                },
                year,
                month,
                day,
            )
            calendar.add(Calendar.DATE, 0)
//            dateDialog.datePicker.minDate = calendar.timeInMillis
//                .show()
            dateDialog.show()
        }

    }
}