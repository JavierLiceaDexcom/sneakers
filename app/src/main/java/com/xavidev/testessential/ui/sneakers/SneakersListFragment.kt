package com.xavidev.testessential.ui.sneakers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.data.source.local.entity.Brand
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import com.xavidev.testessential.databinding.FragmentSneakersListBinding
import com.xavidev.testessential.ui.main.PopulateViewModel
import com.xavidev.testessential.ui.sneakerDetail.OnSneakerListener
import com.xavidev.testessential.ui.sneakers.adapters.*
import com.xavidev.testessential.utils.*
import kotlinx.coroutines.launch

class SneakersListFragment : Fragment() {

    companion object {
        private const val GRID_COUNT = 2
    }

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

    private val populateModel: PopulateViewModel by viewModels {
        ViewModelFactory(
            populateRepository = (requireContext().applicationContext as SneakersApplication).populateRepository,
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

        populateDatabase()

        populateModel.databasePopulatedEvent.observe(viewLifecycleOwner, EventObserver {
            getBrandsAndSneakers()
        })

        viewModel.clearResults.observe(viewLifecycleOwner, EventObserver { filter ->
            if (!filter) {
                brandsAdapter.clearSelectedItem()
            }

            binding.fabClearResults.apply { if (filter) visible() else gone() }
        })

        setSneakersBrands()
        setSneakersList()
    }

    private fun getBrandsAndSneakers() {
        brandsViewModel.getBrands()
        viewModel.getAllSneakersComplete()
    }

    private fun populateDatabase() {
        populateModel.getSneakersCount()

        populateModel.sneakersCount.observe(viewLifecycleOwner) { count ->
            if (count == 0) {
                lifecycleScope.launch {
                    populateModel.populateDatabase(requireContext())
                }
            } else {
                getBrandsAndSneakers()
            }
        }
    }

    private fun setSneakersList() {
        val gridLayoutManager = GridLayoutManager(requireContext(), GRID_COUNT)

        binding.rvSneakers.apply {
            adapter = sneakersAdapter
            layoutManager = gridLayoutManager
        }

        viewModel.sneakersList.observe(viewLifecycleOwner) { sneakers ->
            sneakersAdapter.submitList(sneakers)
        }
    }

    private fun setSneakersBrands() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvSneakerBrands.apply {
            adapter = brandsAdapter
            layoutManager = linearLayoutManager
        }

        brandsViewModel.brandsList.observe(viewLifecycleOwner) { brands ->
            brandsAdapter.submitList(brands)
        }
    }

    //TODO: Remove this method and create a reactive call to update the list
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