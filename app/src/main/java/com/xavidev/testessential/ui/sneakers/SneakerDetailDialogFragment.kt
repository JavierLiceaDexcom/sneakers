package com.xavidev.testessential.ui.sneakers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.databinding.FragmentSneakerDetailDialogBinding
import com.xavidev.testessential.ui.sneakers.adapters.SneakerCarouselAdapter
import com.xavidev.testessential.utils.SneakerCarouselUtils

class SneakerDetailDialogFragment : BottomSheetDialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSneakerDetailDialogBinding.inflate(layoutInflater)
    }

    private lateinit var carouselAdapter: SneakerCarouselAdapter
    private var currentPosition = 0
    private val carouselUtils = SneakerCarouselUtils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onStart() {
        super.onStart()
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments?.getSerializable("sneaker") as Sneaker
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            sneaker = bundle
        }
        val photos = bundle.photos.toList().toMutableList()
        photos.add(0, bundle.thumbnail)
        carouselAdapter = SneakerCarouselAdapter(photos)
        binding.sneakerCarouselViewPager.adapter = carouselAdapter
        carouselUtils.setupCarouselIndicator(
            carouselAdapter,
            binding.lytItemIndicators,
            requireContext()
        )
        setupListeners()
    }

    private fun setupListeners() {
        binding.sneakerCarouselViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                carouselUtils.setCurrentIndicator(
                    position,
                    binding.lytItemIndicators,
                    requireContext()
                )
                currentPosition = position
            }
        })
    }
}