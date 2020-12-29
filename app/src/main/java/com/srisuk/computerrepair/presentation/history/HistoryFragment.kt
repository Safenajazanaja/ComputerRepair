package com.srisuk.computerrepair.presentation.history
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.srisuk.computerrepair.R
import com.srisuk.computerrepair.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : BaseFragment(R.layout.fragment_history) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adt = HistoryAdapter()
        recycler_view.apply {
            val history = dataSource.history(1)
            layoutManager = LinearLayoutManager(context)
            adapter = adt
        }

        val data = dataSource.history(1)
        adt.setList(data)


    }
}