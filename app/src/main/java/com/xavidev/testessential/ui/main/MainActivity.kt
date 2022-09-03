package com.xavidev.testessential.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.xavidev.testessential.R
import com.xavidev.testessential.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: PopulateViewModel by viewModels { PopulateViewModel.Factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        binding.bottomNavigation.setupWithNavController(navController)

        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.purchasesFragment)
        badge.isVisible = true
        badge.number = 99

        viewModel.populateDatabase()
    }
}