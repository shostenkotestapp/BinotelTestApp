package com.binotel.testapp.ui.component.adapter.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binotel.testapp.data.remote.responses.repo.RepoResponseItem
import com.binotel.testapp.databinding.ItemReposBinding

class RepoListAdapter(
    private val item: ArrayList<RepoResponseItem>,
    private val listener: (RepoResponseItem) -> Unit
) :
    RecyclerView.Adapter<RepoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        return RepoListViewHolder(
            ItemReposBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        holder.bindList(item[position], listener)
    }

    override fun getItemCount(): Int = item.size

    fun setData(items: List<RepoResponseItem>) {
        item.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}