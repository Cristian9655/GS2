package com.example.gs2.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clipToBounds
import coil.compose.AsyncImage
import com.example.gs2.AuthState
import com.example.gs2.ClientViewModel
import com.example.gs2.authViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: authViewModel
) {
    val clientViewModel: ClientViewModel = viewModel()
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    var selectedPage by remember { mutableStateOf("overview") }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFECF9EC), // Fundo da barra: Verde claro
                tonalElevation = 4.dp // Elevação leve para destaque
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Visão Geral") },
                    label = { Text("Visão Geral") },
                    selected = selectedPage == "overview",
                    onClick = { selectedPage = "overview" },
                    colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF4CAF50), // Verde
                        unselectedIconColor = Color(0xFFA5D6A7), // Verde mais claro
                        indicatorColor = Color(0xFFDFF5DF) // Destaque no selecionado
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.List, contentDescription = "Clientes") },
                    label = { Text("Clientes") },
                    selected = selectedPage == "clients",
                    onClick = { selectedPage = "clients" },
                    colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFFFC107), // Amarelo Solar
                        unselectedIconColor = Color(0xFFFFE082), // Amarelo claro
                        indicatorColor = Color(0xFFFFF9C4) // Fundo amarelo selecionado
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.List, contentDescription = "Créditos") },
                    label = { Text("Créditos") },
                    selected = selectedPage == "credits",
                    onClick = { selectedPage = "credits" },
                    colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF4CAF50), // Verde
                        unselectedIconColor = Color(0xFFA5D6A7), // Verde mais claro
                        indicatorColor = Color(0xFFDFF5DF) // Fundo verde claro selecionado
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil") },
                    selected = selectedPage == "profile",
                    onClick = { selectedPage = "profile" },
                    colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFFFC107), // Azul
                        unselectedIconColor = Color(0xFFFFE082), // Amarelo claro
                        indicatorColor = Color(0xFFFFF9C4) // Fundo amarelo selecionado
                    )
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFECF9EC)), // Fundo geral: Verde claro
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color(0xFFECF9EC)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SunShare",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50) // Título: Verde vibrante
                )

                TextButton(onClick = {
                    authViewModel.signout()
                }) {
                    Text(
                        text = "Sair",
                        color = Color(0xFFFFC107) // Vermelho suave para o botão de sair
                    )
                }
            }

            Divider(
                color = Color(0xFF4CAF50).copy(alpha = 0.5f),
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Navegação entre as páginas
                when (selectedPage) {
                    "overview" -> {
                        Text(
                            text = "Energia Solar, Compartilhada de Forma Inteligente",
                            fontSize = 28.sp,
                            color = Color(0xFF4CAF50), // Verde para títulos principais
                            fontWeight = FontWeight.Bold,
                            lineHeight = 35.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Transforme seu excesso de energia solar em benefícios reais! O SunShare conecta sistemas solares a consumidores interessados em energia limpa e acessível.",
                            fontSize = 16.sp,
                            color = Color(0xFF388E3C), // Verde escuro
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            textAlign = TextAlign.Justify
                        )

                        Spacer(modifier = Modifier.height(28.dp))

                        AsyncImage(
                            model = "https://www.opencadd.com.br/hubfs/Imported_Blog_Media/web_2020_07_23_EnergiasRenovaveis-2.jpg",
                            contentDescription = "Imagem de exemplo",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(horizontal = 16.dp)
                                .clipToBounds(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    "clients" -> {
                        ClientsPage(
                            navController = navController,
                            clientViewModel = clientViewModel,
                            onClientDetail = { clientId ->
                                navController.navigate("client_detail/$clientId")
                            }
                        )
                    }

                    "profile" -> {
                        ProfilePage(authViewModel = authViewModel)
                    }

                    "credits" -> {
                        CreditsPage(
                            clientViewModel = clientViewModel
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
