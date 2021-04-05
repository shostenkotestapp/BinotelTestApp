package com.binotel.testapp.ui.component.fragment

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.binotel.testapp.R
import com.binotel.testapp.ui.component.adapter.followers.FollowerAdapter
import com.binotel.testapp.databinding.FollowersFragmentLayoutBinding
import com.binotel.testapp.ui.component.detail.DetailViewModel
import com.binotel.testapp.ui.component.detail.DetailActivity
import com.binotel.testapp.utils.visible
import com.google.android.material.snackbar.Snackbar

class FollowersFragment : Fragment(R.layout.followers_fragment_layout) {

    private val binding by viewBinding<FollowersFragmentLayoutBinding>()
    private val followersViewModel by activityViewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argument = arguments
        val username = argument?.getString(DetailActivity.DETAIL_KEY).toString()
        binding.pbContent.visible(true)
        followersViewModel.getFollowers(username)
        followersViewModel.dataFollowers.observe(viewLifecycleOwner) { followers ->
            if (followers != null) {
                binding.rvFollowers.apply {
                    binding.pbContent.visible(false)
                    layoutManager =
                        LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
                    adapter = FollowerAdapter(arrayListOf()) {
                        Snackbar.make(view, it.login ?: "", Snackbar.LENGTH_SHORT).show()
                    }.also { adapter ->
                        adapter.setData(followers)
                        val item = adapter.itemCount
                        if (item == 0 || item < -1) {
                            binding.rvFollowers.visibility = View.INVISIBLE
                        } else
                            binding.rvFollowers.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}



