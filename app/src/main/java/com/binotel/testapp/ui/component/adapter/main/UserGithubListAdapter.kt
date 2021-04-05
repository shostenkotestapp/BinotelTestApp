package com.binotel.testapp.ui.component.adapter.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binotel.testapp.data.remote.responses.search.Item
import com.binotel.testapp.databinding.ItemUserRowBinding

class UserGithubListAdapter(
    private val item: ArrayList<Item>,
    private val listener: (Item) -> Unit
) :
    RecyclerView.Adapter<UserGithubListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGithubListViewHolder {
        return UserGithubListViewHolder(
            ItemUserRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserGithubListViewHolder, position: Int) {
        holder.bindList(item[position], listener)
    }

    override fun getItemCount(): Int = item.size

    fun setData(items: List<Item>) {
        item.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}