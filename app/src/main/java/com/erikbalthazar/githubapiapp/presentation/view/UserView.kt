package com.erikbalthazar.githubapiapp.presentation.view

import com.erikbalthazar.githubapiapp.data.model.User

interface UserView {
    fun showUsers(users: List<User>)
    fun showError(message: String)
}