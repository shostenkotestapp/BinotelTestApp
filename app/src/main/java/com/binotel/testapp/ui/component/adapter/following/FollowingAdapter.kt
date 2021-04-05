package com.binotel.testapp.ui.component.adapter.following

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binotel.testapp.data.remote.responses.following.FollowingResponse
import com.binotel.testapp.databinding.ItemFollowRowBinding


class FollowingAdapter(
    val item: ArrayList<FollowingResponse>,
    private val listener: (FollowingResponse) -> Unit
) : RecyclerView.Adapter<FollowingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder =
        FollowingViewHolder(
            ItemFollowRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) =
        holder.bindList(item[position], listener)

    override fun getItemCount(): Int = item.size

    fun setData(items: List<FollowingResponse>) {
        item.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}