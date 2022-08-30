package com.xavidev.testessential.ui.sale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.xavidev.testessential.databinding.ActivitySaleBinding

class SaleActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySaleBinding.inflate(layoutInflater)
    }

    private val sneakers: SaleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}