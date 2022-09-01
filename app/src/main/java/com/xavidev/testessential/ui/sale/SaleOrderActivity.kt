package com.xavidev.testessential.ui.sale

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xavidev.testessential.databinding.ActivitySaleOrderBinding

class SaleOrderActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySaleOrderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}