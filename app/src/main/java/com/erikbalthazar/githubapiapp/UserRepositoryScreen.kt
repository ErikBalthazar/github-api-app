package com.erikbalthazar.githubapiapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.erikbalthazar.githubapiapp.ui.components.RepositoryListItemCard
import com.erikbalthazar.githubapiapp.viewmodel.UserRepositoryViewModel

@Composable
fun UserRepositoryScreen(navController: NavHostController, username: String?) {
    val userRepositoryViewModel: UserRepositoryViewModel = viewModel()

    val repositories by userRepositoryViewModel.repositories.observeAsState(emptyList())
    val errorMessage by userRepositoryViewModel.errorMessage.observeAsState("")

    username?.let { userRepositoryViewModel.getUserRepos(it) }

    Scaffold(
        modifier = Modifier.fillMaxWidth()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(repositories) { repo ->
                RepositoryListItemCard(
                    name = repo.name,
                    description = repo.description,
                    language = repo.language
                )
            }
        }

        if (errorMessage.isNotEmpty()) {
        }
    }
}