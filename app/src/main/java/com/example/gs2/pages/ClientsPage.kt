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
import androidx.compose.foundation.clickable
import androidx.compose.material3.*

@Composable
fun ClientsPage(
    navController: NavController,
    clientViewModel: ClientViewModel,
    onClientDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val clients = clientViewModel.clients.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECF9EC)) // Fundo: Verde claro
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título da página
        Text(
            text = "Lista de Clientes",
            fontSize = 28.sp,
            color = Color(0xFF4CAF50), // Verde vibrante
            fontWeight = FontWeight.Bold,
            lineHeight = 35.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Botão para adicionar novo cliente
        Button(
            onClick = { navController.navigate("add_client") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Botão verde
        ) {
            Text("Adicionar Novo Cliente", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Lista de clientes
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(clients.value) { client ->
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

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Button(
                        onClick = {
                            onUpdate(client.copy(name = editedName, cpf = editedCPF, phone = editedPhone))
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
