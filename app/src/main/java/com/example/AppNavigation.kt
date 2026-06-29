package com.example

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import androidx.compose.ui.Modifier

@Composable
fun AppNavigation(viewModel: SovereignViewModel, chatViewModel: ChatViewModel) {
    val navController = rememberNavController()
    val items = listOf("Dashboard" to Icons.Default.Home, "Chat" to Icons.Default.Email)
    var selectedItem by remember { mutableIntStateOf(0) }
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, (label, icon) ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index; navController.navigate(label) },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "Dashboard", modifier = Modifier.padding(innerPadding)) {
            composable("Dashboard") { SovereignDashboard(viewModel) }
            composable("Chat") { ChatScreen(chatViewModel) }
        }
    }
}
