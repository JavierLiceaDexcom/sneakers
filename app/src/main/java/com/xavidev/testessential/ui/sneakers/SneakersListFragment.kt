package com.xavidev.testessential.ui.sneakers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavidev.testessential.R
import com.xavidev.testessential.data.source.local.entity.Brand
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import com.xavidev.testessential.databinding.FragmentSneakersListBinding
import com.xavidev.testessential.ui.sneakers.adapters.*
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.utils.ViewModelFactory
import com.xavidev.testessential.utils.toast

class SneakersListFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSneakersListBinding.inflate(layoutInflater)
    }

    private val viewModel by activityViewModels<SneakersListFragmentViewModel> {
        ViewModelFactory(
            sneakersRepository = (requireContext().applicationContext as SneakersApplication).sneakersRepository,
            owner = this
        )
    }

    private val brandsViewModel by activityViewModels<BrandsViewModel> {
        ViewModelFactory(
            brandsRepository = (requireContext().applicationContext as SneakersApplication).brandsRepository,
            owner = this
        )
    }

    private val sneakerItemClickListener = object : SneakerItemClickListener {
        override fun invoke(sneaker: SneakerComplete, pos: Int) {
            val action = SneakersListFragmentDirections
                .actionSneakersListFragmentToSneakerDetailDialogFragment(sneaker.id)
            viewModel.navigateTo(view!!, action)
        }
    }

    private val sneakerFavoriteClickListener = object : SneakerFavoriteClickListener {
        override fun invoke(sneaker: SneakerComplete, pos: Int) {
            setSneakerFavorite(sneaker, pos)
        }
    }

    private val sneakersAdapter =
        SneakersAdapter(sneakerItemClickListener, sneakerFavoriteClickListener)

    private val brandItemClickListener = object : BrandItemClickListener {
        override fun invoke(brand: Brand, pos: Int) {
            viewModel.getSneakersByBrand(brand.id)
        }
    }

    private val brandsAdapter = BrandsAdapter(brandItemClickListener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        getSneakersBrands()
        getSneakersList()

        viewModel.clearResults.observe(viewLifecycleOwner) { filter ->
            if (!filter) {
                brandsAdapter.clearSelectedItem()
            }
        }
    }

    private fun getSneakersList() {
        viewModel.getAllSneakersComplete()
        binding.recyclerSneakers.apply {
            adapter = sneakersAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.sneakersList.observe(viewLifecycleOwner) { sneakers ->
            sneakersAdapter.submitList(sneakers)
        }
    }

    private fun getSneakersBrands() {
        brandsViewModel.getBrands()
        binding.recyclerSneakerBrands.apply {
            adapter = brandsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        brandsViewModel.brandsList.observe(viewLifecycleOwner) { brands ->
            brandsAdapter.submitList(brands)
        }
    }

    private fun setSneakerFavorite(sneaker: SneakerComplete, pos: Int) {
        sneakersAdapter.updateItem(sneaker, pos)
        val value = sneaker.favorite.not()
        sneaker.favorite = value
        viewModel.setFavorite(sneaker.id, value)
        showFavoriteUpdateToast(value)
    }

    private fun showFavoriteUpdateToast(value: Boolean) {
        val message =
            if (value) getString(R.string.text_favorites_added) else getString(R.string.text_favorite_removed)
        requireActivity().toast(message)
    }
}