package com.example.gs2.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

data class Client(
    val id: String = "",
    val name: String = "",
    val cpf: String = "",
    val phone: String = "",
    val email: String = "",
    val cep: String = "",
    val energyGenerated: Double = 0.0, // Energia gerada em kW/h
    val energyAvailable: Double = 0.0, // Energia disponível em kW/h
    val energyDistributor: String = "" // Nome da distribuidora de energia
)


class ClientRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val clientsCollection = firestore.collection("clients")

    // Obter todos os clientes
    suspend fun getClients(): List<Client> {
        return try {
            clientsCollection.get().await().documents.mapNotNull { it.toObject<Client>()?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Adicionar novo cliente
    suspend fun addClient(client: Client) {
        clientsCollection.add(client).await()
    }

    // Atualizar dados do cliente
    suspend fun updateClient(client: Client) {
        client.id.takeIf { it.isNotEmpty() }?.let {
            clientsCollection.document(it).set(client).await()
        }
    }

    // Remover cliente
    suspend fun deleteClient(clientId: String) {
        clientsCollection.document(clientId).delete().await()
    }
}
