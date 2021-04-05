package com.binotel.testapp.ui.component.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binotel.testapp.data.remote.responses.detail.DetailResponse
import com.binotel.testapp.data.remote.responses.follower.FollowersResponses
import com.binotel.testapp.data.remote.responses.following.FollowingResponses
import com.binotel.testapp.data.remote.responses.repo.RepoResponse
import com.binotel.testapp.repository.SearchGithubRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val api: SearchGithubRepo
) : ViewModel() {

    private val _dataDetail = MutableLiveData<DetailResponse>()
    val dataDetail: LiveData<DetailResponse> = _dataDetail
    private val _dataFollowers = MutableLiveData<FollowersResponses>()
    val dataFollowers: LiveData<FollowersResponses> = _dataFollowers
    private val _dataFollowing = MutableLiveData<FollowingResponses>()
    val dataFollowing: LiveData<FollowingResponses> = _dataFollowing
    private val _dataRepos = MutableLiveData<RepoResponse>()
    val dataRepos: LiveData<RepoResponse> = _dataRepos

    fun getDataDetail(username: String) = viewModelScope.launch {
        api.getDetailUser(username).also {
            if (it.isSuccessful) {
                _dataDetail.value = it.body()
            }
        }
    }

    fun getFollowers(userName: String) = viewModelScope.launch {
        api.getFollower(userName).also {
            if (it.isSuccessful) {
                _dataFollowers.value = it.body()
            }
        }
    }


    fun getFollowing(userName: String) = viewModelScope.launch {
        api.getFollowing(userName).also {
            if (it.isSuccessful) {
                _dataFollowing.value = it.body()
            }
        }
    }

    fun getRepos(userName: String) = viewModelScope.launch {
        api.getUserRepos(userName).also {
            if (it.isSuccessful) {
                _dataRepos.value = it.body()
            }
        }
    }
}