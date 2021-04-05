package com.binotel.testapp.ui.component.adapter.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.binotel.testapp.ui.component.fragment.FollowersFragment
import com.binotel.testapp.ui.component.fragment.FollowingFragment
import com.binotel.testapp.ui.component.fragment.RepoFragment

class PagerAdapter(activity: AppCompatActivity, data: Bundle) : FragmentStateAdapter(activity) {
    private var bundles: Bundle = data

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = RepoFragment()
            1 -> fragment = FollowersFragment()
            2 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.bundles
        return fragment as Fragment
    }

    override fun getItemCount() = 3
}