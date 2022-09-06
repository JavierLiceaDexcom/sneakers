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
import com.xavidev.testessential.data.entity.SneakerComplete
import com.xavidev.testessential.data.entity.toCart
import com.xavidev.testessential.databinding.FragmentSneakersListBinding
import com.xavidev.testessential.ui.sneakers.adapters.BrandsAdapter
import com.xavidev.testessential.ui.sneakers.adapters.SneakersAdapter
import com.xavidev.testessential.utils.toast

class SneakersListFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSneakersListBinding.inflate(layoutInflater)
    }

    private val viewModel: SneakersViewModel by activityViewModels { SneakersViewModel.Factory() }

    private val sneakersAdapter = SneakersAdapter(
        object : (SneakerComplete, Int) -> Unit {
            override fun invoke(sneaker: SneakerComplete, pos: Int) {
                viewModel.setSneakerComplete(sneaker)
                viewModel.navigateTo(
                    view!!,
                    R.id.action_sneakersListFragment_to_sneakerDetailDialogFragment
                )
            }
        },
        object : (SneakerComplete, Int) -> Unit {
            override fun invoke(sneaker: SneakerComplete, pos: Int) {
                setSneakerFavorite(sneaker, pos)
            }
        },
        object : (SneakerComplete, Int) -> Unit {
            override fun invoke(sneaker: SneakerComplete, pos: Int) {
                requireActivity().toast(getString(R.string.text_sneaker_added_to_cart))
                viewModel.addSneakerToCart(sneaker.toCart())
            }
        },
    )

    private val brandsAdapter = BrandsAdapter(object : (Brand, Int) -> Unit {
        override fun invoke(brand: Brand, pos: Int) {
            viewModel.getSneakersByBrand(brand.id)
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

        viewModel.clearResults.observe(viewLifecycleOwner) { clear ->
            if (!clear) {
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