package com.example.gs2.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gs2.ClientViewModel
import com.example.gs2.repository.Client

@Composable
fun CreditsPage(
    clientViewModel: ClientViewModel,
    modifier: Modifier = Modifier
) {
    val clients = clientViewModel.clients.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECF9EC)) // Fundo: Verde claro
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título da Página
        Text(
            text = "Créditos Disponíveis",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50), // Verde vibrante
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de clientes
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(clients.value) { client ->
                ExpandableCreditClientItem(client = client)
            }
        }
    }
}

@Composable
fun ExpandableCreditClientItem(client: Client) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .background(Color.Transparent),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Cabeçalho com Nome e Energia Disponível
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = client.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF388E3C) // Verde escuro
                    )
                    Text(
                        text = "Energia Disponível: ${client.energyAvailable} kW/h",
                        fontSize = 14.sp,
                        color = Color(0xFF00796B) // Verde azulado
                    )
                }
                Text(
                    text = if (isExpanded) "Esconder ↑" else "Ver Mais ↓",
                    fontSize = 14.sp,
                    color = Color(0xFF4CAF50) // Verde vibrante
                )
            }

            // Detalhes (visíveis somente se expandido)
            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF1F8E9), shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Text("Celular: ${client.phone}", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("E-mail: ${client.email}", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("CEP: ${client.cep}", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Energia Gerada: ${client.energyGenerated} kW/h", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Distribuidora: ${client.energyDistributor}", fontSize = 16.sp, color = Color(0xFF757575))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botão "Adquirir"
            Button(
                onClick = { /* Sem função */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)) // Amarelo solar
            ) {
                Text(text = "Adquirir", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}
