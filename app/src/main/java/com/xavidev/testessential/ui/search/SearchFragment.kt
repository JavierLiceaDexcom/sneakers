package com.xavidev.testessential.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.data.source.local.entity.Brand
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import com.xavidev.testessential.databinding.FragmentSearchBinding
import com.xavidev.testessential.ui.search.adapters.SneakerSearchAdapter
import com.xavidev.testessential.ui.sneakers.BrandsViewModel
import com.xavidev.testessential.ui.sneakers.adapters.BrandItemClickListener
import com.xavidev.testessential.ui.sneakers.adapters.BrandsAdapter
import com.xavidev.testessential.ui.sneakers.adapters.SneakerItemClickListener
import com.xavidev.testessential.utils.ViewModelFactory

class SearchFragment : Fragment() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    private val viewModel: SearchViewModel by activityViewModels {
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
            val action = SearchFragmentDirections
                .actionSearchFragmentToSneakerDetailDialogFragment(sneaker.id)
            viewModel.navigateTo(view!!, action)
        }
    }

    private val sneakersAdapter =
        SneakerSearchAdapter(sneakerItemClickListener)

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

        binding.recyclerSneakerBrands.apply {
            adapter = brandsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        brandsViewModel.getBrands()

        setupListeners()
        loadBrandsList()
        loadSneakersList()
    }

    private fun loadSneakersList() {
        binding.recyclerSearchSneakers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = sneakersAdapter
        }

        viewModel.sneakers.observe(viewLifecycleOwner) { list ->
            sneakersAdapter.submitList(list)
        }
    }

    private fun loadBrandsList() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.recyclerSneakerBrands.apply {
            layoutManager = linearLayoutManager
            adapter = brandsAdapter
        }

        brandsViewModel.brandsList.observe(viewLifecycleOwner) { list ->
            brandsAdapter.submitList(list)
        }
    }

    private fun setupListeners() {
        binding.etSearchSneakers.addTextChangedListener {
            it?.let { query -> viewModel.searchSneakersByName(query.toString()) }
        }
    }
}