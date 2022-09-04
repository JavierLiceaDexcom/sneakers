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
import com.xavidev.testessential.data.entity.Brand
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.databinding.FragmentSneakersListBinding
import com.xavidev.testessential.ui.sneakers.adapters.BrandsAdapter
import com.xavidev.testessential.ui.sneakers.adapters.SneakersAdapter

class SneakersListFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSneakersListBinding.inflate(layoutInflater)
    }

    private val viewModel: SneakersViewModel by activityViewModels { SneakersViewModel.Factory() }

    private val sneakersAdapter = SneakersAdapter(
        object : (Sneaker, Int) -> Unit {
            override fun invoke(sneaker: Sneaker, pos: Int) {
                viewModel.setSneaker(sneaker)
                viewModel.navigateTo(
                    view!!,
                    R.id.action_sneakersListFragment_to_sneakerDetailDialogFragment
                )
            }
        },
        object : (Sneaker, Int) -> Unit {
            override fun invoke(sneaker: Sneaker, pos: Int) {
                // Code here for favorite
            }
        },
        object : (Sneaker, Int) -> Unit {
            override fun invoke(sneaker: Sneaker, pos: Int) {
                // Code here to add to the cart
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
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        getSneakersBrands()
        getSneakersList()
    }

    private fun getSneakersList() {
        viewModel.getAllSneakers()
        binding.recyclerSneakers.apply {
            adapter = sneakersAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.sneakersList.observe(viewLifecycleOwner) { sneakers ->
            sneakersAdapter.submitList(sneakers)
        }
    }

    private fun getSneakersBrands() {
        viewModel.getBrands()
        binding.recyclerSneakerBrands.apply {
            adapter = brandsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.brandsList.observe(viewLifecycleOwner) { brands ->
            brandsAdapter.submitList(brands)
        }
    }
}