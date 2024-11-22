package com.example.gs2.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.gs2.AuthViewModel

@Composable
fun ProfilePage(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val userName = authViewModel.getUserName() ?: "User"
    val userEmail = authViewModel.getUserEmail() ?: "No Email"

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Page", fontSize = 24.sp)
        Text(text = "Name: $userName", fontSize = 16.sp)
        Text(text = "Email: $userEmail", fontSize = 16.sp)
    }
}
