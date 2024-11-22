package com.example.gs2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gs2.pages.CreditsPage
import com.example.gs2.pages.HomePage
import com.example.gs2.pages.ProfilePage
import com.example.gs2.pages.RegisterPage
import com.example.gs2.pages.LoginPage
import com.example.gs2.pages.SignupPage

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(modifier, navController, authViewModel)
        }
        composable("signup") {
            SignupPage(modifier, navController, authViewModel)
        }
        composable("main") {
            MainScreen(modifier, navController, authViewModel)
        }
    }

}
