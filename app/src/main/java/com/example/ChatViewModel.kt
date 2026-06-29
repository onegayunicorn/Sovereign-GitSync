package com.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    val messages = MutableStateFlow<List<Pair<String, Boolean>>>(emptyList())
    val isLoading = MutableStateFlow(false)

    fun sendMessage(prompt: String) {
        viewModelScope.launch {
            isLoading.value = true
            messages.value = messages.value + (prompt to true)
            try {
                val response = RetrofitClient.instance.sendMessage(
                    ChatRequest(message = prompt)
                )
                val reply = response.response
                messages.value = messages.value + (reply to false)
            } catch (e: Exception) {
                messages.value = messages.value + ("Error: ${e.message}" to false)
            }
            isLoading.value = false
        }
    }
}
