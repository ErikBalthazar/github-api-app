package com.erikbalthazar.githubapiapp.data.network

import com.erikbalthazar.githubapiapp.data.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {
    @GET("users")
    fun getUsers(@Query("q") searchQuery: String): Call<List<User>>
}