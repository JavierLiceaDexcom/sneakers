package com.xavidev.testessential.ui.search

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavidev.testessential.data.entity.Brand
import com.xavidev.testessential.databinding.FragmentSearchBinding
import com.xavidev.testessential.databinding.FragmentSneakersListBinding
import com.xavidev.testessential.ui.sneakers.SneakersViewModel
import com.xavidev.testessential.ui.sneakers.adapters.BrandsAdapter
import com.xavidev.testessential.utils.toast

class SearchFragment : Fragment() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    private val viewModel: SneakersViewModel by activityViewModels()

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

        binding.recyclerSneakerBrands.apply {
            adapter = brandsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.getBrandList().observe(viewLifecycleOwner) { brands ->
            brandsAdapter.submitList(brands)
        }
        setupListeners()
    }

    private fun setupListeners() {
        binding.fieldSearchSneakers.addOnEndIconChangedListener { textInputLayout, _ ->
            textInputLayout.editText?.setText("")
        }

        binding.fieldSearchSneakers.editText?.addTextChangedListener {
            it?.let { searchSneakers(it.toString()) }
        }
    }

    private fun searchSneakers(query: String) {
        // Code here
    }
}