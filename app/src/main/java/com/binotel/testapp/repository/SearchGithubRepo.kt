package com.binotel.testapp.repository

import com.binotel.testapp.data.remote.client.GithubClient
import com.binotel.testapp.data.remote.responses.detail.DetailResponse
import com.binotel.testapp.data.remote.responses.follower.FollowersResponses
import com.binotel.testapp.data.remote.responses.following.FollowingResponses
import com.binotel.testapp.data.remote.responses.repo.RepoResponse
import com.binotel.testapp.data.remote.responses.search.SearchUserResponse
import retrofit2.Response
import javax.inject.Inject

class SearchGithubRepo @Inject constructor(
    private val apiService: GithubClient
) {
    suspend fun getSearchUser(query: String): Response<SearchUserResponse> =
        apiService.getSearchUser(query)

    suspend fun getDetailUser(username: String): Response<DetailResponse> =
        apiService.getDetailUser(username)

    suspend fun getFollower(username: String): Response<FollowersResponses> =
        apiService.getUserFollowers(username)

    suspend fun getFollowing(username: String): Response<FollowingResponses> =
        apiService.getUserFollowing(username)
    suspend fun getUserRepos(username: String): Response<RepoResponse> =
        apiService.getUserRepos(username)
}