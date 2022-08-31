package com.xavidev.testessential.ui.sneakers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavidev.testessential.R
import com.xavidev.testessential.data.entity.Brand
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.databinding.FragmentSneakersListBinding
import com.xavidev.testessential.ui.sneakers.adapters.BrandsAdapter
import com.xavidev.testessential.ui.sneakers.adapters.SneakersAdapter
import com.xavidev.testessential.utils.toast


class SneakersListFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSneakersListBinding.inflate(layoutInflater)
    }

    private val viewModel: SneakersViewModel by activityViewModels()
    private val sneakersAdapter = SneakersAdapter(
        object : (Sneaker, Int) -> Unit {
            override fun invoke(sneaker: Sneaker, pos: Int) {
                val bundle = bundleOf("sneaker" to sneaker)
                viewModel.navigateTo(view!!, R.id.action_sneakersListFragment_to_sneakerDetailDialogFragment, bundle)
            }
        },
        object : (Sneaker, Int) -> Unit {
            override fun invoke(sneaker: Sneaker, pos: Int) {
                // Code here
            }
        },
        object : (Sneaker, Int) -> Unit {
            override fun invoke(sneaker: Sneaker, pos: Int) {
                // Code here
            }
        },
    )

    private val brandsAdapter = BrandsAdapter(object : (Brand, Int) -> Unit {
        override fun invoke(brand: Brand, pos: Int) {
            brand.selected = !brand.selected
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

        }

        binding.recyclerSneakers.apply {
            adapter = sneakersAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        binding.recyclerSneakerBrands.apply {
            adapter = brandsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.getSneakersList().observe(viewLifecycleOwner) { sneakers ->
            sneakersAdapter.submitList(sneakers)
        }

        viewModel.getBrandList().observe(viewLifecycleOwner) { brands ->
            brandsAdapter.submitList(brands)
        }
    }
}