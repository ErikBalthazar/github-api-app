package com.erikbalthazar.githubapiapp.presentation.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erikbalthazar.githubapiapp.data.model.GithubUserResponse
import com.erikbalthazar.githubapiapp.data.model.User
import com.erikbalthazar.githubapiapp.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getUsers(query: String) {
        RetrofitClient.instance.getUsers(query).enqueue(object : Callback<GithubUserResponse> {
            override fun onResponse(call: Call<GithubUserResponse>, response: Response<GithubUserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _users.value = it.userList
                    }
                } else {
                    _errorMessage.value = "Error fetching users"
                }
            }

            override fun onFailure(call: Call<GithubUserResponse>, t: Throwable) {
                _errorMessage.value = "Error: ${t.localizedMessage}"
            }
        })
    }
}
