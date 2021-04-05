package com.binotel.testapp.ui.component.adapter.main

import androidx.recyclerview.widget.RecyclerView
import com.binotel.testapp.data.remote.responses.search.Item
import com.binotel.testapp.databinding.ItemUserRowBinding
import com.bumptech.glide.Glide


class UserGithubListViewHolder(private val binding: ItemUserRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindList(data: Item, listener: (Item) -> Unit) {
        binding.tvUsername.text = data.login ?: ""
        Glide.with(binding.rootView)
            .load(data.avatarUrl ?: "")
            .into(binding.ivUser)
        binding.root.setOnClickListener { listener(data) }
    }
}