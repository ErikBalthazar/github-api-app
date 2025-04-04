package com.erikbalthazar.githubapiapp.data.model

import com.google.gson.annotations.SerializedName

data class GithubUserResponse(
    @SerializedName("items")
    val userList: List<User>
)
