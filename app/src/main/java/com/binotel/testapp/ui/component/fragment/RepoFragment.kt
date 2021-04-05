package com.binotel.testapp.ui.component.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.binotel.testapp.R
import com.binotel.testapp.databinding.RepoFragmentLayoutBinding
import com.binotel.testapp.ui.component.detail.DetailActivity
import com.binotel.testapp.ui.component.detail.ReposDetailActivity
import com.binotel.testapp.ui.component.adapter.repos.RepoListAdapter
import com.binotel.testapp.ui.component.detail.DetailViewModel
import com.binotel.testapp.utils.visible

class RepoFragment : Fragment(R.layout.repo_fragment_layout) {

    private val binding by viewBinding<RepoFragmentLayoutBinding>()
    private val repoViewModel by activityViewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argument = arguments
        val username = argument?.getString(DetailActivity.DETAIL_KEY).toString()
        binding.pbContent.visible(true)
        repoViewModel.getRepos(username)
        binding.apply {
            repoViewModel.dataRepos.observe(viewLifecycleOwner) { dataRepos ->
                rvRepo.apply {
                    binding.pbContent.visible(false)
                    layoutManager =
                        LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
                    adapter = RepoListAdapter(arrayListOf()) {
                        startActivity(Intent(activity, ReposDetailActivity::class.java).apply {
                            putExtra(ReposDetailActivity.DETAIL_NAME_REPO, it.fullName ?: "")
                            putExtra(ReposDetailActivity.DETAIL_LANG, it.language ?: "")
                            putExtra(ReposDetailActivity.DETAIL_URL, it.archiveUrl ?: "")
                        })
                    }.also { adapter ->
                        adapter.setData(dataRepos)
                        val item = adapter.itemCount
                        if (item == 0 || item < -1) {
                            rvRepo.visibility = View.INVISIBLE
                        } else
                            rvRepo.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}