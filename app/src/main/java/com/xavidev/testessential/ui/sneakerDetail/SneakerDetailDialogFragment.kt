package com.xavidev.testessential.ui.sneakerDetail

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.data.source.local.entity.Images
import com.xavidev.testessential.databinding.FragmentSneakerDetailDialogBinding
import com.xavidev.testessential.ui.sale.SaleOrderActivity
import com.xavidev.testessential.ui.sneakerDetail.adapters.SneakerCarouselAdapter
import com.xavidev.testessential.ui.sneakers.adapters.ColorClickListener
import com.xavidev.testessential.ui.sneakers.adapters.SneakerColorsSelectionAdapter
import com.xavidev.testessential.utils.*

class SneakerDetailDialogFragment : DialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSneakerDetailDialogBinding.inflate(layoutInflater)
    }

    private val args: SneakerDetailDialogFragmentArgs by navArgs()

    private val viewModel by viewModels<SneakerDetailDialogFragmentViewModel> {
        ViewModelFactory(
            sneakersRepository = (requireContext().applicationContext as SneakersApplication).sneakersRepository,
            cartRepository = (requireContext().applicationContext as SneakersApplication).cartRepository,
            owner = this
        )
    }
    private lateinit var carouselAdapter: SneakerCarouselAdapter
    private var currentPosition = 0
    private val carouselUtils = SneakerCarouselUtils()

    private val sneakerColorClickListener = object : ColorClickListener {
        override fun invoke(color: String, pos: Int) {
            viewModel.setColorSelected()
        }
    }

    private val sneakerColorsAdapter = SneakerColorsSelectionAdapter(sneakerColorClickListener)

    private var onSneakerListener: OnSneakerListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun getTheme() = R.style.FullscreenDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onSneakerListener = context as OnSneakerListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        val sneakerId = args.sneakerId
        viewModel.getSneaker(sneakerId)

        handleListeners()
        handleObservers()
    }

    private fun handleListeners() = with(binding) {
        sneakerCarouselViewPager.registerOnPageChangeCallback(object :
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

        chpGroupSizes.setOnCheckedChangeListener { chipGroup, checkedId ->
            val size = chipGroup.findViewById<Chip>(checkedId)?.text
            viewModel.setSizeSelected()
        }

        tbrSneakerDetails.setNavigationOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun handleObservers() {
        view?.setupSnackbar(viewLifecycleOwner, viewModel.errorMessages, Snackbar.LENGTH_SHORT)

        view?.setupSnackbar(viewLifecycleOwner, viewModel.addedToCartMessage, Snackbar.LENGTH_SHORT)

        view?.setupSnackbar(viewLifecycleOwner, viewModel.favoriteMessage, Snackbar.LENGTH_SHORT)

        viewModel.sneaker.observe(viewLifecycleOwner) { sneaker ->
            setSizesListSelection(sneaker.sizes)
            setColorsAdapter(sneaker.colors)
            viewModel.getSneakerImages(sneaker.photosId)
        }

        viewModel.sneakerImages.observe(viewLifecycleOwner) { images -> setCarouselAdapter(images) }

        viewModel.buySneakerEvent.observe(viewLifecycleOwner, EventObserver {
            if (!viewModel.validateSizeAndColor()) return@EventObserver
            viewModel.startBuySneaker(requireActivity(), SaleOrderActivity())
        })

        viewModel.sneakerFavoriteEvent.observe(viewLifecycleOwner, EventObserver {
            onSneakerListener?.onSneakerFavorite()
        })

        viewModel.sneakerCartEvent.observe(viewLifecycleOwner, EventObserver {
            onSneakerListener?.onSneakerCart()
        })
    }

    private fun setColorsAdapter(colors: List<String>) {
        binding.recyclerSneakerColors.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = sneakerColorsAdapter
        }
        sneakerColorsAdapter.submitList(colors)
    }

    private fun setSizesListSelection(sizes: List<Double>) {
        sizes.forEach { size -> binding.chpGroupSizes.addChip(requireContext(), "$size") }
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
}

interface OnSneakerListener {
    fun onSneakerFavorite()

    fun onSneakerCart()
}