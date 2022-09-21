package com.xavidev.testessential.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.databinding.ActivityMainBinding
import com.xavidev.testessential.utils.ViewModelFactory
import com.xavidev.testessential.utils.toast

class MainActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: PopulateViewModel by viewModels {
        ViewModelFactory(
            populateRepository = (this.applicationContext as SneakersApplication).populateRepository,
            owner = this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.purchasesFragment)
        badge.isVisible = true
        badge.number = 99

        viewModel.getSneakersCount()
        viewModel.sneakersCount.observe(this) { count ->
            if (count == 0) {
                viewModel.populateDatabase()
            }
        }
    }
}