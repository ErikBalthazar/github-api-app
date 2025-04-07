package com.erikbalthazar.githubapiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.erikbalthazar.githubapiapp.data.model.User
import com.erikbalthazar.githubapiapp.presentation.presenter.UserPresenter
import com.erikbalthazar.githubapiapp.presentation.ui.components.ListItemCard
import com.erikbalthazar.githubapiapp.presentation.ui.components.SearchBar
import com.erikbalthazar.githubapiapp.presentation.ui.theme.GithubAPIAppTheme
import com.erikbalthazar.githubapiapp.presentation.view.UserView

class MainActivity : ComponentActivity(), UserView {
    private lateinit var presenter: UserPresenter
    private var searchQuery by mutableStateOf("")
    private val users = mutableStateListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        presenter = UserPresenter(this)

        setContent {
            GithubAPIAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        SearchBar(searchQuery) { query ->
                            query?.let { presenter.fetchUsers(it) }
                        }
                    }
                ) { innerPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(users) { user ->
                            ListItemCard(user.avatarUrl, user.login)
                        }
                    }
                }
            }
        }
    }

    override fun showUsers(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
    }

    override fun showError(message: String) {}
}