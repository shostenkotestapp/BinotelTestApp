package com.binotel.testapp.ui.component.detail

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.annotation.StringRes
import com.binotel.testapp.R
import com.binotel.testapp.ui.component.adapter.main.PagerAdapter
import com.binotel.testapp.databinding.ActivityDetailBinding
import com.binotel.testapp.ui.base.BaseActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : BaseActivity() {

    private val binding by viewBinding<ActivityDetailBinding>()
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myUsername = intent.getStringExtra(DETAIL_KEY) ?: ""
        val myAvatar = intent.getStringExtra(DETAIL_AVATAR) ?: ""
        if (myUsername.isNotEmpty()) {
            detailViewModel.getDataDetail(myUsername)
        }
        val bundle = Bundle().apply {
            putString(DETAIL_KEY, myUsername)
        }
        val pagerAdapter = PagerAdapter(this, bundle)
        binding.viewPagerId.adapter = pagerAdapter
        TabLayoutMediator(binding.tabsId, binding.viewPagerId) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    override fun observeViewModel() {
        detailViewModel.dataDetail.observe(this) { dataDetail ->
            if (dataDetail != null) {
                binding.apply {
                    Glide.with(root)
                        .load(dataDetail.avatarUrl)
                        .into(ivProfilDetail)
                    tvFullName.text = dataDetail.name ?: ""
                    tvUserName.text = dataDetail.login ?: ""
                    tvUserLocation.text = dataDetail.location ?: ""
                    tvFollowerCount.text = dataDetail.followers.toString()
                    tvFollowingCount.text = dataDetail.following.toString()
                }
            }
        }
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.repo, R.string.followers, R.string.following)
        const val DETAIL_KEY = "detail_key"
        const val DETAIL_ID = "detail_id"
        const val DETAIL_AVATAR = "avatar_url"
    }
}