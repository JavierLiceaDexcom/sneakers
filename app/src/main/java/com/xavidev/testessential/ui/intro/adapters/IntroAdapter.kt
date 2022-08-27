package com.xavidev.testessential.ui.intro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.databinding.ItemOnboardingContainerBinding
import com.xavidev.testessential.ui.intro.IntroItem

class IntroAdapter(private val list: List<IntroItem>) :
    RecyclerView.Adapter<IntroAdapter.IntroViewHolder>() {

    inner class IntroViewHolder(private val binding: ItemOnboardingContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: IntroItem) {
            binding.idOnboardingImage.setImageResource(item.image)
            binding.idTitleOnboarding.text = item.title
            binding.idMessageOnboarding.text = item.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemOnboardingContainerBinding.inflate(layoutInflater, parent, false)
        return IntroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int = list.size
}