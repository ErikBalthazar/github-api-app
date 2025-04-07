package com.erikbalthazar.githubapiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.erikbalthazar.githubapiapp.navigation.Nav
import com.erikbalthazar.githubapiapp.presentation.ui.theme.GithubAPIAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubAPIAppTheme {
                Nav()
            }
        }
    }
}