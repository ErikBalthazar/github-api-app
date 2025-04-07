package com.erikbalthazar.githubapiapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.erikbalthazar.githubapiapp.ui.components.ListItemCard
import com.erikbalthazar.githubapiapp.ui.components.SearchBar
import com.erikbalthazar.githubapiapp.viewmodel.UserViewModel

@Composable
fun UserScreen(navController: NavHostController) {
    val userViewModel: UserViewModel = viewModel()

    val users by userViewModel.users.observeAsState(emptyList())
    val errorMessage by userViewModel.errorMessage.observeAsState("")

    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchBar(
                searchQuery = searchQuery,
                onSearchClick = { query ->
                    if (!query.isNullOrEmpty()) {
                        searchQuery = query
                        userViewModel.getUsers(query)
                    }
                }
            )
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

        if (errorMessage.isNotEmpty()) {
        }
    }
}
