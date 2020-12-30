package com.srisuk.computerrepair.presentation.history
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : BaseFragment(R.layout.fragment_history) {
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


    }
}