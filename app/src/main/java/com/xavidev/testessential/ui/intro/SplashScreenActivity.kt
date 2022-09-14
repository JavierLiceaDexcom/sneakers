package com.xavidev.testessential.ui.intro

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.xavidev.testessential.ui.main.MainActivity
import com.xavidev.testessential.utils.startNewActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: IntroViewModel by viewModels { IntroViewModel.Factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.introPassed.observe(this) { passed ->
            val targetActivity = if (passed) MainActivity() else IntroActivity()
            startNewActivity(targetActivity, finish = true)
        }
    }
}