package com.example.myvideoplayer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myvideoplayer.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityHistoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }
}