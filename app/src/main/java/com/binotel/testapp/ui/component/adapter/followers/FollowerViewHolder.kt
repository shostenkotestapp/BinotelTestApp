package com.binotel.testapp.ui.component.adapter.followers

import androidx.recyclerview.widget.RecyclerView
import com.binotel.testapp.data.remote.responses.follower.FollowersResponse
import com.binotel.testapp.databinding.ItemFollowRowBinding
import com.bumptech.glide.Glide


class FollowerViewHolder(private val binding: ItemFollowRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindList(data: FollowersResponse, listener: (FollowersResponse) -> Unit) {
        binding.apply {
            Glide.with(root)
                .load(data.avatarUrl ?: "")
                .into(ivFollower)
            tvUsernameFollower.text = data.login ?: ""
            root.setOnClickListener { listener(data) }
        }
    }
}