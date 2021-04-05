package com.binotel.testapp.data.remote.client

import com.binotel.testapp.data.remote.responses.detail.DetailResponse
import com.binotel.testapp.data.remote.responses.follower.FollowersResponses
import com.binotel.testapp.data.remote.responses.following.FollowingResponses
import com.binotel.testapp.data.remote.responses.repo.RepoResponse
import com.binotel.testapp.data.remote.responses.search.SearchUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubClient {
    @GET("search/users")
    suspend fun getSearchUser(@Query("q") query: String): Response<SearchUserResponse>

    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") userName: String): Response<DetailResponse>

    @GET("users/{username}/followers")
    suspend fun getUserFollowers(@Path("username") userName: String): Response<FollowersResponses>

    @GET("users/{username}/following")
    suspend fun getUserFollowing(@Path("username") userName: String): Response<FollowingResponses>

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") userName: String): Response<RepoResponse>
}