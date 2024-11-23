package com.example.gs2.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.gs2.authViewModel

@Composable
fun ProfilePage(modifier: Modifier = Modifier, authViewModel: authViewModel) {
    val userName = authViewModel.getUserName() ?: "Usuário"
    val userEmail = authViewModel.getUserEmail() ?: "Email não disponível"

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECF9EC))
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Perfil do Cliente",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50),
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp),
            textAlign = TextAlign.Center
        )


        Box(
            modifier = Modifier
                .size(140.dp)
                .background(Color.White, shape = androidx.compose.foundation.shape.CircleShape)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberImagePainter("https://theonebrief.com/wp-content/uploads/2019/05/energia-renovavel.jpg"),
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(132.dp)
                    .background(Color(0xFFECF9EC), shape = androidx.compose.foundation.shape.CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        ProfileInfoItem(label = "Nome", value = userName)

        ProfileInfoItem(label = "Email", value = userEmail)

        Spacer(modifier = Modifier.height(40.dp))

    }
}

@Composable
fun ProfileInfoItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF388E3C),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}
