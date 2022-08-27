package com.xavidev.testessential.ui.sneakers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.databinding.ItemCarouselImageBinding
import com.xavidev.testessential.databinding.ItemOnboardingContainerBinding
import com.xavidev.testessential.ui.intro.IntroItem

class SneakerCarouselAdapter(private val list: List<String>) :
    RecyclerView.Adapter<SneakerCarouselAdapter.SneakerCarouselViewHolder>() {

    inner class SneakerCarouselViewHolder(private val binding: ItemCarouselImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(imageUrl: String) = with(binding) {
            url = imageUrl
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerCarouselViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCarouselImageBinding.inflate(layoutInflater, parent, false)
        return SneakerCarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SneakerCarouselViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int = list.size
}