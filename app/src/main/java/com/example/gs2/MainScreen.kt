package com.example.gs2

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gs2.pages.CreditsPage
import com.example.gs2.pages.HomePage
import com.example.gs2.pages.ProfilePage
import com.example.gs2.pages.RegisterPage

@Composable
fun MainScreen(modifier: Modifier = Modifier, mainNavController: NavHostController, authViewModel: AuthViewModel) {
    val bottomNavController = rememberNavController()
    val currentPage = remember { mutableStateOf("home") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = currentPage.value == "home",
                    onClick = {
                        currentPage.value = "home"
                        bottomNavController.navigate("home") {
                            popUpTo("home") { inclusive = false }
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Receipt, contentDescription = "Credits") },
                    label = { Text("Credits") },
                    selected = currentPage.value == "credits",
                    onClick = {
                        currentPage.value = "credits"
                        bottomNavController.navigate("credits") {
                            popUpTo("home") { inclusive = false }
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Register") },
                    label = { Text("Register") },
                    selected = currentPage.value == "register",
                    onClick = {
                        currentPage.value = "register"
                        bottomNavController.navigate("register") {
                            popUpTo("home") { inclusive = false }
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = currentPage.value == "profile",
                    onClick = {
                        currentPage.value = "profile"
                        bottomNavController.navigate("profile") {
                            popUpTo("home") { inclusive = false }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomePage(modifier, mainNavController, authViewModel)
            }
            composable("credits") {
                CreditsPage(modifier, mainNavController)
            }
            composable("register") {
                RegisterPage(modifier, mainNavController)
            }
            composable("profile") {
                ProfilePage(modifier, authViewModel)
            }
        }
    }
}
