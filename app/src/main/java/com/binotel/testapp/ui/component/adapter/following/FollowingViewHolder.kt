package com.binotel.testapp.ui.component.adapter.following

import androidx.recyclerview.widget.RecyclerView
import com.binotel.testapp.data.remote.responses.following.FollowingResponse
import com.binotel.testapp.databinding.ItemFollowRowBinding
import com.bumptech.glide.Glide

class FollowingViewHolder(private val binding: ItemFollowRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindList(data: FollowingResponse, listener: (FollowingResponse) -> Unit) {
        binding.apply {
            Glide.with(root)
                .load(data.avatarUrl ?: "")
                .into(ivFollower)
            tvUsernameFollower.text = data.login ?: ""
            root.setOnClickListener { listener(data) }
        }

    }
}