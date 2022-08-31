package com.xavidev.testessential.ui.sneakers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.databinding.FragmentSneakerDetailDialogBinding
import com.xavidev.testessential.ui.sneakers.adapters.SneakerCarouselAdapter
import com.xavidev.testessential.ui.sneakers.adapters.SneakerColorsAdapter
import com.xavidev.testessential.ui.sneakers.adapters.SneakerSizesAdapter
import com.xavidev.testessential.utils.SneakerCarouselUtils
import com.xavidev.testessential.utils.toast

class SneakerDetailDialogFragment : BottomSheetDialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSneakerDetailDialogBinding.inflate(layoutInflater)
    }

    private val viewModel: SneakersViewModel by viewModels<SneakersViewModel>()
    private lateinit var carouselAdapter: SneakerCarouselAdapter
    private var currentPosition = 0
    private val carouselUtils = SneakerCarouselUtils()
    private val sneakerSizesAdapter = SneakerSizesAdapter(object : (Double, Int) -> Unit {
        override fun invoke(size: Double, pos: Int) {
            requireContext().toast("Size $size in position $pos")
        }
    })
    private val sneakerColorsAdapter = SneakerColorsAdapter(object : (Int, Int) -> Unit {
        override fun invoke(color: Int, pos: Int) {
            requireContext().toast("Color $color in position $pos")
        }
    })

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
        viewModel.setSneaker(bundle)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            sneaker = bundle
        }

        setCarouselAdapter(bundle)
        setSizesAdapter(bundle)
        setColorsAdapter(bundle)
        setupListeners()
    }

    private fun setColorsAdapter(bundle: Sneaker) {
        binding.recyclerSneakerColors.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = sneakerColorsAdapter
        }
        sneakerColorsAdapter.submitList(bundle.colors)
    }

    private fun setSizesAdapter(bundle: Sneaker) {
        binding.recyclerSneakerSizes.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = sneakerSizesAdapter
        }
        sneakerSizesAdapter.submitList(bundle.sizes)
    }

    private fun setCarouselAdapter(bundle: Sneaker) {
        val photos = bundle.photos.toList().toMutableList()
        photos.add(0, bundle.thumbnail)
        carouselAdapter = SneakerCarouselAdapter(photos)
        binding.sneakerCarouselViewPager.adapter = carouselAdapter
        carouselUtils.setupCarouselIndicator(
            carouselAdapter,
            binding.lytItemIndicators,
            requireContext()
        )
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