package com.erikbalthazar.githubapiapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erikbalthazar.githubapiapp.UserRepositoryScreen
import com.erikbalthazar.githubapiapp.UserScreen

@Composable
fun Nav() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "userScreen") {
        composable(route = "userScreen") {
            UserScreen(navController)
        }
        composable(
            route = "userRepositoryScreen/{username}",
            arguments = listOf(
                navArgument(name = "username") {
                    type = NavType.StringType
                }
            )
        ) { backstackEntry ->
            UserRepositoryScreen(
                navController,
                username = backstackEntry.arguments?.getString("username")
            )
        }
    }
}