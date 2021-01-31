package com.srisuk.computerrepair.presentation.history
import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.data.request.HistoryRequest
import com.srisuk.computerrepair.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_history.*
import java.text.SimpleDateFormat
import java.util.*


class HistoryFragment : BaseFragment(R.layout.fragment_history) {
    private var mCalendarstar: Calendar? = null
    private var mCalendarend: Calendar? = null
    private var start:Long?=null
    private var end:Long?=null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userId =context?.getSharedPreferences("file",
            AppCompatActivity.MODE_PRIVATE)?.getInt("userId",0)


//        val sdf = SimpleDateFormat("yyyy/MM/dd")
//        val dateTimeStr = sdf.format(start)



        Bt_history_datestar.setOnClickListener {
            val calendar = mCalendarstar ?: Calendar.getInstance()

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
                    Bt_history_datestar.setText("$year/${month.plus(1)}/$dayOfMonth")


                    val calendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
                    start = calendar.timeInMillis.toLong()
                    Log.d(TAG, "onActivityCreated:"+calendar.timeInMillis)
//                    start="$year-${month.plus(1)}-$dayOfMonth"
//                    Log.d(TAG, "onActivityCreated:$start ")

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
        Bt_history_dateend.setOnClickListener {
            val calendar = mCalendarend ?: Calendar.getInstance()

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
                    Bt_history_dateend.setText("$year/${month.plus(1)}/$dayOfMonth")



                    val calendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
//                    end="$year-${month.plus(1)}-$dayOfMonth"
                    end = calendar.timeInMillis.toLong()

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

        bt_search.setOnClickListener {
            val adt = HistoryAdapter()
            val req = start?.let { it1 -> end?.let { it2 -> HistoryRequest(userId = userId!!,date = it1,dateEnd = it2) } }
            val data = req?.let { it1 -> dataSource.history(it1) }

            recycler_view.apply {
                val data = req?.let { it1 -> dataSource.history(it1) }
                layoutManager = LinearLayoutManager(context)
                adapter = adt
            }

            adt.setList(data)
        }




    }
}