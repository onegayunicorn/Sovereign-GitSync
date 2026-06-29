package com.example

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    val messages by viewModel.messages.collectAsState()
    var text by remember { mutableStateOf("") }
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages) { (msg, isUser) ->
                Text(text = if(isUser) "You: $msg" else "AI: $msg", modifier = Modifier.padding(8.dp))
            }
        }
        Row(modifier = Modifier.padding(top = 8.dp)) {
            TextField(value = text, onValueChange = { text = it }, modifier = Modifier.weight(1f))
            Button(onClick = { viewModel.sendMessage(text); text = "" }, modifier = Modifier.padding(start = 8.dp)) { Text("Send") }
        }
    }
}
