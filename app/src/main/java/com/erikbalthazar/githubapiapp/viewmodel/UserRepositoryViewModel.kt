package com.erikbalthazar.githubapiapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erikbalthazar.githubapiapp.data.model.Repository
import com.erikbalthazar.githubapiapp.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryViewModel: ViewModel() {
    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>> get() = _repositories

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getUserRepos(username: String) {
        RetrofitClient.instance.getUserRepos(username).enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _repositories.value = it
                    }
                } else {
                    _errorMessage.value = "Error fetching repositories"
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                _errorMessage.value = "Error: ${t.localizedMessage}"
            }
        })
    }
}