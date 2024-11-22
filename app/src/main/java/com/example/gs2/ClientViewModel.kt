package com.example.gs2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gs2.repository.Client
import com.example.gs2.repository.ClientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClientViewModel : ViewModel() {

    private val repository = ClientRepository()

    private val _clients = MutableStateFlow<List<Client>>(emptyList())
    val clients: StateFlow<List<Client>> = _clients

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    init {
        fetchClients()
    }

    fun fetchClients() {
        viewModelScope.launch {
            _loading.value = true
            _clients.value = repository.getClients()
            _loading.value = false
        }
    }

    fun addClient(client: Client) {
        viewModelScope.launch {
            repository.addClient(client)
            fetchClients()
        }
    }

    fun updateClient(client: Client) {
        viewModelScope.launch {
            repository.updateClient(client)
            fetchClients()
        }
    }

    fun deleteClient(clientId: String) {
        viewModelScope.launch {
            repository.deleteClient(clientId)
            fetchClients()
        }
    }
}
