package com.binotel.testapp.ui.component.fragment

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.binotel.testapp.R
import com.binotel.testapp.ui.component.adapter.following.FollowingAdapter
import com.binotel.testapp.databinding.FollowingFragmentLayoutBinding
import com.binotel.testapp.ui.component.detail.DetailViewModel
import com.binotel.testapp.ui.component.detail.DetailActivity
import com.binotel.testapp.utils.visible
import com.google.android.material.snackbar.Snackbar

class FollowingFragment : Fragment(R.layout.following_fragment_layout) {

    private val binding by viewBinding<FollowingFragmentLayoutBinding>()
    private val followingViewModel by activityViewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val argument = arguments
        val username = argument?.getString(DetailActivity.DETAIL_KEY).toString()
        binding.pbContent.visible(true)
        followingViewModel.getFollowing(username)
        binding.apply {
            followingViewModel.dataFollowing.observe(viewLifecycleOwner) { followingData ->
                rvFollowing.apply {
                    binding.pbContent.visible(false)
                    layoutManager =
                        LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
                    adapter = FollowingAdapter(arrayListOf()) {
                        Snackbar.make(view, it.login ?: "", Snackbar.LENGTH_SHORT).show()
                    }.also { adapter ->
                        adapter.setData(followingData)
                        val item = adapter.itemCount
                        if (item == 0 || item < -1) {
                            rvFollowing.visibility = View.INVISIBLE
                        } else
                            rvFollowing.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}