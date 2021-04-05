package com.binotel.testapp.ui.component.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import com.binotel.testapp.R
import com.binotel.testapp.databinding.ActivityRepoDetailsBinding


class ReposDetailActivity : AppCompatActivity() {
    val binding: ActivityRepoDetailsBinding by viewBinding()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val detailNameRepo = intent.getStringExtra(DETAIL_NAME_REPO) ?: ""
        val detailLang = intent.getStringExtra(DETAIL_LANG) ?: ""
        val link = intent.getStringExtra(DETAIL_URL) ?: ""
        binding.apply {
            tvRepoName.text = "${resources.getString(R.string.detail_name_repo_title)} $detailNameRepo"
            tvRepoLanguage.text = "${resources.getString(R.string.detail_Lang_title)} $detailLang"
            tvRepoLink.text = "${resources.getString(R.string.detail_repo_link_title)} $link"
        }
    }

    companion object {
        const val DETAIL_NAME_REPO = "detail_name_repo"
        const val DETAIL_LANG = "detail_lang"
        const val DETAIL_URL = "archive_link"
    }
}