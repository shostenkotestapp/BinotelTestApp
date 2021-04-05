package com.binotel.testapp.ui.component.adapter.repos

import androidx.recyclerview.widget.RecyclerView
import com.binotel.testapp.data.remote.responses.repo.RepoResponseItem
import com.binotel.testapp.databinding.ItemReposBinding


class RepoListViewHolder(private val binding: ItemReposBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindList(data: RepoResponseItem, listener: (RepoResponseItem) -> Unit) {
        binding.apply {
            tvRepoName.text = data.fullName ?: ""
            tvRepoLanguage.text = data.language ?: ""
            root.setOnClickListener { listener(data) }
        }
    }
}