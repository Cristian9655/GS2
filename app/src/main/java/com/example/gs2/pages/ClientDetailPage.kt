package com.example.gs2.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gs2.ClientViewModel

@Composable
fun ClientDetailPage(clientId: String, clientViewModel: ClientViewModel = viewModel()) {
    val clients = clientViewModel.clients.collectAsState()
    val client = clients.value.find { it.id == clientId }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFE3F2FD), Color(0xFFE5F4FB)) // Gradiente azul claro
                )
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (client != null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Título
                    Text(
                        text = "Detalhes do Cliente",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1565C0), // Azul vibrante
                        textAlign = TextAlign.Center
                    )

                    // Linha de separação
                    Divider(
                        color = Color(0xFFBBDEFB),
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    // Informações do cliente
                    DetailItem(label = "Nome", value = client.name)
                    DetailItem(label = "CPF", value = client.cpf)
                    DetailItem(label = "Celular", value = client.phone)
                    DetailItem(label = "E-mail", value = client.email)
                    DetailItem(label = "CEP", value = client.cep)
                    DetailItem(
                        label = "Energia Gerada",
                        value = "${client.energyGenerated} kW/h"
                    )
                    DetailItem(
                        label = "Energia Disponível",
                        value = "${client.energyAvailable} kW/h"
                    )
                    DetailItem(label = "Distribuidora", value = client.energyDistributor)
                    DetailItem(
                        label = "Consumo Médio",
                        value = "${client.averageConsumption} kW/h"
                    )
                }
            }
        } else {
            Text(
                text = "Carregando cliente...",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E88E5), // Azul escuro
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}
