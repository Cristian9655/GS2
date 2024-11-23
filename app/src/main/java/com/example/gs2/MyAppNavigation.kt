package com.example.gs2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gs2.pages.AddClientPage
import com.example.gs2.pages.ClientDetailPage
import com.example.gs2.pages.ClientsPage
import com.example.gs2.pages.CreditsPage
import com.example.gs2.pages.HomePage
import com.example.gs2.pages.LoginPage
import com.example.gs2.pages.SignupPage

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: authViewModel) {
    val navController = rememberNavController()
    val clientViewModel: ClientViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(modifier, navController, authViewModel)
        }
        composable("signup") {
            SignupPage(modifier, navController, authViewModel)
        }
        composable("home") {
            HomePage(modifier, navController, authViewModel)
        }
        composable("credits") {
            CreditsPage(
                clientViewModel = clientViewModel
            )
        }
        composable("clients") {
            ClientsPage(
                navController = navController,
                clientViewModel = clientViewModel,
                onClientDetail = { clientId ->
                    navController.navigate("client_detail/$clientId")
                },
                authViewModel = authViewModel
            )
        }

        composable("add_client") {
            AddClientPage(
                navController = navController,
                clientViewModel = clientViewModel,
                authViewModel = authViewModel
            )
        }
        composable(
            route = "client_detail/{clientId}",
            arguments = listOf(navArgument("clientId") { type = NavType.StringType })
        ) { backStackEntry ->
            val clientId = backStackEntry.arguments?.getString("clientId")
            if (clientId != null) {
                ClientDetailPage(clientId = clientId, clientViewModel = clientViewModel)
            }
        }
    }
}
