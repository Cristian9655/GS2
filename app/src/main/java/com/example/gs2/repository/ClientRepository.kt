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
    val energyGenerated: Double = 0.0,
    val energyAvailable: Double = 0.0,
    val energyDistributor: String = "",
    val averageConsumption: Double = 0.0
)



class ClientRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val clientsCollection = firestore.collection("clients")

    suspend fun getClients(): List<Client> {
        return try {
            clientsCollection.get().await().documents.mapNotNull { it.toObject<Client>()?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun addClient(client: Client) {
        clientsCollection.add(client).await()
    }

    suspend fun updateClient(client: Client) {
        client.id.takeIf { it.isNotEmpty() }?.let {
            clientsCollection.document(it).set(client).await()
        }
    }

    suspend fun deleteClient(clientId: String) {
        clientsCollection.document(clientId).delete().await()
    }
}
