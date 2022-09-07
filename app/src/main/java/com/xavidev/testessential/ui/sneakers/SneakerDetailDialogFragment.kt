package com.xavidev.testessential.ui.sneakers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xavidev.testessential.data.entity.Images
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.databinding.FragmentSneakerDetailDialogBinding
import com.xavidev.testessential.ui.sale.SaleOrderActivity
import com.xavidev.testessential.ui.sneakers.adapters.SneakerCarouselAdapter
import com.xavidev.testessential.ui.sneakers.adapters.SneakerColorsAdapter
import com.xavidev.testessential.ui.sneakers.adapters.SneakerSizesAdapter
import com.xavidev.testessential.utils.SneakerCarouselUtils
import com.xavidev.testessential.utils.toast

class SneakerDetailDialogFragment : BottomSheetDialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSneakerDetailDialogBinding.inflate(layoutInflater)
    }
    val args: SneakerDetailDialogFragmentArgs by navArgs()

    private val viewModel: SneakersViewModel by viewModels { SneakersViewModel.Factory() }
    private lateinit var carouselAdapter: SneakerCarouselAdapter
    private var currentPosition = 0
    private val carouselUtils = SneakerCarouselUtils()
    private val sneakerSizesAdapter = SneakerSizesAdapter(object : (Double, Int) -> Unit {
        override fun invoke(size: Double, pos: Int) {
            requireContext().toast("Size $size in position $pos")
        }
    })
    private val sneakerColorsAdapter = SneakerColorsAdapter(object : (String, Int) -> Unit {
        override fun invoke(color: String, pos: Int) {
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

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        val sneakerId = args.sneakerId
        viewModel.getSneaker(sneakerId)

        binding.btnBuyNow.setOnClickListener {
            viewModel.onBuySneaker(requireActivity(), SaleOrderActivity())
        }

        viewModel.sneaker.observe(viewLifecycleOwner) { sneaker ->
            setSizesAdapter(sneaker.sizes)
            setColorsAdapter(sneaker.colors)
            viewModel.getSneakerImages(sneaker.photosId)
        }

        viewModel.sneakerImages.observe(viewLifecycleOwner) { images -> setCarouselAdapter(images) }
        setupListeners()
    }

    private fun setColorsAdapter(colors: List<String>) {
        binding.recyclerSneakerColors.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = sneakerColorsAdapter
        }
        sneakerColorsAdapter.submitList(colors)
    }

    private fun setSizesAdapter(sizes: List<Double>) {
        binding.recyclerSneakerSizes.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = sneakerSizesAdapter
        }
        sneakerSizesAdapter.submitList(sizes)
    }

    private fun setCarouselAdapter(images: Images) {
        carouselAdapter = SneakerCarouselAdapter(images.images)
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