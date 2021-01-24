package com.srisuk.computerrepair.presentation.history
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : BaseFragment(R.layout.fragment_history) {
    private var mCalendar: Calendar? = null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userId =context?.getSharedPreferences("file",
            AppCompatActivity.MODE_PRIVATE)?.getInt("userId",0)
        val adt = HistoryAdapter()
        recycler_view.apply {
            val history = userId?.let { dataSource.history(it) }
            layoutManager = LinearLayoutManager(context)
            adapter = adt
        }

        val data = userId?.let { dataSource.history(it) }
        adt.setList(data)
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