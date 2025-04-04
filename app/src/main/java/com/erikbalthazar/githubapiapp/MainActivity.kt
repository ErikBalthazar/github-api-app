package com.erikbalthazar.githubapiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.erikbalthazar.githubapiapp.data.model.User
import com.erikbalthazar.githubapiapp.presentation.presenter.UserPresenter
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

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchClick: (String?) -> Unit
) {
    var text by remember { mutableStateOf(searchQuery) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Search...") }
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(onClick = { onSearchClick(text) }) {
            Text(text = "Search", fontSize = 18.sp)
        }
    }
}

@Composable
fun ListItemCard(imageUrl: String, title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Item Image",
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                fontSize = 20.sp
            )
        }
    }
}