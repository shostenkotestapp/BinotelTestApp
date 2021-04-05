package com.binotel.testapp.ui.component.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.binotel.testapp.R
import com.binotel.testapp.ui.component.adapter.main.UserGithubListAdapter
import com.binotel.testapp.databinding.ActivityMainBinding
import com.binotel.testapp.ui.base.BaseActivity
import com.binotel.testapp.ui.component.detail.DetailActivity
import com.binotel.testapp.utils.hideKeyboard
import com.binotel.testapp.utils.onAction
import com.binotel.testapp.utils.visible
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btSearch.setOnClickListener {
            hideKeyboard()
            searchUser(binding.etSearch.text.toString())
        }
        binding.etSearch.onAction {
            hideKeyboard()
            searchUser(binding.etSearch.text.toString())
        }
    }

    override fun observeViewModel() {
        mainViewModel.dataSearchUser.observe(this) { data ->
            binding.rvList.apply {
                binding.pbContent.visible(false)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = UserGithubListAdapter(arrayListOf()) { items ->
                    startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.DETAIL_KEY, items.login ?: "")
                        putExtra(DetailActivity.DETAIL_ID, items.id ?: "")
                        putExtra(DetailActivity.DETAIL_AVATAR, items.avatarUrl ?: "")
                    })
                }.also {
                    it.setData(data.items)
                    if (it.itemCount == -1) {
                        binding.rvList.visibility = View.GONE
                    } else {
                        binding.rvList.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun searchUser(name: String) {
        if (name.isNotEmpty()) {
            binding.pbContent.visible(true)
            mainViewModel.getSearchUser(name)
        } else {
            Toast.makeText(this, resources.getString(R.string.edit_name), Toast.LENGTH_SHORT).show()
        }

    }
}