package com.example.gs2.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gs2.ClientViewModel
import com.example.gs2.repository.Client
import androidx.navigation.NavController
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import com.example.gs2.authViewModel

@Composable
fun ClientsPage(
    navController: NavController,
    clientViewModel: ClientViewModel,
    onClientDetail: (String) -> Unit,
    authViewModel: authViewModel,
    modifier: Modifier = Modifier
) {
    val clients = clientViewModel.clients.collectAsState()
    val userEmail = authViewModel.getUserEmail()

    val filteredClients = remember(clients.value, userEmail) {
        clients.value.filter { it.email == userEmail }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECF9EC))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Seja locador",
            fontSize = 28.sp,
            color = Color(0xFF4CAF50),
            fontWeight = FontWeight.Bold,
            lineHeight = 35.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.navigate("add_client") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Adicionar Novo Credito", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(filteredClients) { client ->
                ClientItem(
                    client = client,
                    onDelete = { clientViewModel.deleteClient(client.id) },
                    onUpdate = { updatedClient -> clientViewModel.updateClient(updatedClient) },
                    onDetail = { onClientDetail(client.id) }
                )
            }
        }
    }
}


@Composable
fun ClientItem(
    client: Client,
    onDelete: (String) -> Unit,
    onUpdate: (Client) -> Unit,
    onDetail: () -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var editedName by remember { mutableStateOf(client.name) }
    var editedCPF by remember { mutableStateOf(client.cpf) }
    var editedPhone by remember { mutableStateOf(client.phone) }
    var editedCEP by remember { mutableStateOf(client.cep) }
    var editedEnergyGenerated by remember { mutableStateOf(client.energyGenerated.toString()) }
    var editedEnergyAvailable by remember { mutableStateOf(client.energyAvailable.toString()) }
    var editedDistributor by remember { mutableStateOf(client.energyDistributor) }
    var editedAverageConsumption by remember { mutableStateOf(client.averageConsumption.toString()) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(1.dp, Color(0xFFE0E0E0), shape = RoundedCornerShape(10.dp))
            .padding(16.dp)
    ) {
        Column {
            if (isEditing) {
                TextField(
                    value = editedName,
                    onValueChange = { editedName = it },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = editedCPF,
                    onValueChange = { editedCPF = it },
                    label = { Text("CPF") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = editedPhone,
                    onValueChange = { editedPhone = it },
                    label = { Text("Celular") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = editedCEP,
                    onValueChange = { editedCEP = it },
                    label = { Text("CEP") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = editedEnergyGenerated,
                    onValueChange = { editedEnergyGenerated = it },
                    label = { Text("Energia Gerada (kW/h)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = editedEnergyAvailable,
                    onValueChange = { editedEnergyAvailable = it },
                    label = { Text("Energia Disponível (kW/h)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = editedDistributor,
                    onValueChange = { editedDistributor = it },
                    label = { Text("Distribuidora de Energia") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = editedAverageConsumption,
                    onValueChange = { editedAverageConsumption = it },
                    label = { Text("Consumo Médio (kW/h)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Button(
                        onClick = {
                            val updatedClient = client.copy(
                                name = editedName,
                                cpf = editedCPF,
                                phone = editedPhone,
                                cep = editedCEP,
                                energyGenerated = editedEnergyGenerated.toDoubleOrNull() ?: 0.0,
                                energyAvailable = editedEnergyAvailable.toDoubleOrNull() ?: 0.0,
                                energyDistributor = editedDistributor,
                                averageConsumption = editedAverageConsumption.toDoubleOrNull() ?: 0.0
                            )
                            onUpdate(updatedClient)
                            isEditing = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text("Salvar", color = Color.White)
                    }
                    Button(
                        onClick = { isEditing = false },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
                    ) {
                        Text("Cancelar", color = Color.White)
                    }
                }
            } else {
                Text("Nome: ${client.name}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF424242))
                Spacer(modifier = Modifier.height(4.dp))
                Text("CPF: ${client.cpf}", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Celular: ${client.phone}", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text("CEP: ${client.cep}", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Energia Gerada: ${client.energyGenerated} kW/h", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Energia Disponível: ${client.energyAvailable} kW/h", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Distribuidora: ${client.energyDistributor}", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Consumo Médio: ${client.averageConsumption} kW/h", fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    OutlinedButton(
                        onClick = onDetail,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Detalhes")
                    }
                    OutlinedButton(
                        onClick = { isEditing = true },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Editar")
                    }
                    OutlinedButton(
                        onClick = { onDelete(client.id) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFF44336))
                    ) {
                        Text("Excluir")
                    }
                }
            }
        }
    }
}

