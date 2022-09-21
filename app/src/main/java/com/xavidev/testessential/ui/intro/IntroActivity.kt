package com.xavidev.testessential.ui.intro

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.databinding.ActivityIntroBinding
import com.xavidev.testessential.ui.main.MainActivity
import com.xavidev.testessential.ui.intro.adapters.IntroAdapter
import com.xavidev.testessential.utils.IntroUtils
import com.xavidev.testessential.utils.ViewModelFactory
import com.xavidev.testessential.utils.startNewActivity
import com.xavidev.testessential.utils.toast

class IntroActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityIntroBinding.inflate(layoutInflater)
    }

    private lateinit var introAdapter: IntroAdapter
    private var currentPosition = 0
    private val viewModel: IntroViewModel by viewModels {
        ViewModelFactory(
            keyValueRepository = (this.applicationContext as SneakersApplication).ketValueRepository,
            owner = this
        )
    }
    val introUtils = IntroUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        introAdapter = IntroAdapter(introUtils.getIntroItems(this))
        binding.onboardingViewPager.adapter = introAdapter
        introUtils.setupIntroIndicator(introAdapter, binding.lytIndicators, this)
        setupListeners()
        viewModelObservers()
    }

    private fun viewModelObservers() = with(viewModel) {
        introPassedLoader.observe(this@IntroActivity) { loading ->
            if (loading) {
                toast("Loading...")
            }
        }

        introPassed.observe(this@IntroActivity) { passed ->
            if (passed) {
                toast("Intro passed")
                startNewActivity(targetActivity = MainActivity(), finish = true)
            }
        }
    }

    private fun setButtonText(isLastItem: Boolean) {
        binding.btnOnboarding.text =
            if (isLastItem) getString(R.string.text_finish) else getString(R.string.text_next)
    }

    private fun setupListeners() {
        binding.onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                introUtils.setCurrentIndicator(position, binding.lytIndicators, this@IntroActivity)
                currentPosition = position
                binding.rootScreen.setBackgroundResource(introAdapter.getList()[position].backgroundColor)
                val isLastItem = currentPosition == introAdapter.itemCount.minus(1)
                setButtonText(isLastItem)
            }
        })

        binding.btnOnboarding.setOnClickListener {
            val totalItems = introAdapter.itemCount
            if (currentPosition < totalItems.minus(1)) {
                introUtils.setCurrentIndicator(currentPosition.plus(1), binding.lytIndicators, this)
                binding.onboardingViewPager.currentItem = currentPosition.plus(1)
            } else {
                viewModel.setIntroPassed()
            }
        }
    }
}