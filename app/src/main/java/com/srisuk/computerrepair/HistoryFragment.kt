package com.srisuk.computerrepair

<<<<<<< HEAD
import androidx.fragment.app.Fragment

class HistoryFragment: Fragment(R.layout.fragment_history) {
=======
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HistoryFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_history)
    }
>>>>>>> safe
}