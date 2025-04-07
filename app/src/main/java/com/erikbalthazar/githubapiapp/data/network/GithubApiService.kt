package com.erikbalthazar.githubapiapp.data.network

import com.erikbalthazar.githubapiapp.data.model.GithubUserResponse
import com.erikbalthazar.githubapiapp.data.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    @GET("search/users")
    fun getUsers(@Query("q") searchQuery: String): Call<GithubUserResponse>

    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Call<List<Repository>>
}