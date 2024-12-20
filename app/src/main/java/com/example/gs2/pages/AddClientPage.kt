package com.example.gs2.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gs2.ClientViewModel
import com.example.gs2.authViewModel
import com.example.gs2.repository.Client
import kotlinx.coroutines.launch

@Composable
fun AddClientPage(
    navController: NavController,
    clientViewModel: ClientViewModel,
    authViewModel: authViewModel,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    val userName = authViewModel.getUserName() ?: ""
    val userEmail = authViewModel.getUserEmail() ?: ""


    var clientName by remember { mutableStateOf(userName) }
    var clientCPF by remember { mutableStateOf("") }
    var clientPhone by remember { mutableStateOf("") }
    var clientEmail by remember { mutableStateOf(userEmail) }
    var clientCEP by remember { mutableStateOf("") }
    var energyGenerated by remember { mutableStateOf("") }
    var energyAvailable by remember { mutableStateOf("") }
    var energyDistributor by remember { mutableStateOf("") }
    var averageConsumption by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Adicionar Cliente",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        CustomTextField(
            value = clientName,
            onValueChange = { clientName = it },
            label = "Nome do Cliente"
        )

        CustomTextField(
            value = clientCPF,
            onValueChange = { clientCPF = it },
            label = "CPF"
        )

        CustomTextField(
            value = clientPhone,
            onValueChange = { clientPhone = it },
            label = "Celular"
        )

        CustomTextField(
            value = clientEmail,
            onValueChange = { clientEmail = it },
            label = "E-mail de Contato",
            enabled = false
        )

        CustomTextField(
            value = clientCEP,
            onValueChange = { clientCEP = it },
            label = "CEP"
        )

        CustomTextField(
            value = energyGenerated,
            onValueChange = { energyGenerated = it },
            label = "Energia Gerada (kW/h)"
        )

        CustomTextField(
            value = energyAvailable,
            onValueChange = { energyAvailable = it },
            label = "Energia Disponível (kW/h)"
        )

        CustomTextField(
            value = energyDistributor,
            onValueChange = { energyDistributor = it },
            label = "Distribuidora de Energia"
        )

        CustomTextField(
            value = averageConsumption,
            onValueChange = { averageConsumption = it },
            label = "Consumo Médio (kW/h)"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    clientViewModel.addClient(
                        Client(
                            name = clientName,
                            cpf = clientCPF,
                            phone = clientPhone,
                            email = clientEmail,
                            cep = clientCEP,
                            energyGenerated = energyGenerated.toDoubleOrNull() ?: 0.0,
                            energyAvailable = energyAvailable.toDoubleOrNull() ?: 0.0,
                            energyDistributor = energyDistributor,
                            averageConsumption = averageConsumption.toDoubleOrNull() ?: 0.0
                        )
                    )
                    navController.navigate("clients")
                }
            },
            enabled = clientName.isNotBlank() && clientCPF.isNotBlank() && clientPhone.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Adicionar Cliente", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.SemiBold)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean = true
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
            focusedTextColor = MaterialTheme.colorScheme.onSurface, // Cor do texto
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface // Cor do texto sem foco
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        enabled = enabled
    )
}

