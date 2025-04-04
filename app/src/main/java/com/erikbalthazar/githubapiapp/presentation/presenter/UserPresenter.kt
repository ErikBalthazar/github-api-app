package com.erikbalthazar.githubapiapp.presentation.presenter

import com.erikbalthazar.githubapiapp.data.model.User
import com.erikbalthazar.githubapiapp.data.network.RetrofitClient
import com.erikbalthazar.githubapiapp.presentation.view.UserView
import retrofit2.Call
import retrofit2.Response

class UserPresenter(private val view: UserView) {

    fun fetchUsers(searchQuery: String) {
        RetrofitClient.instance.getUsers(searchQuery).enqueue(object: retrofit2.Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        view.showUsers(it)
                    }
                } else {
                    view.showError("Error 1")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                view.showError("Error 2")
            }
        })
    }
}