package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.data.SovereignDatabase
import com.example.data.SovereignRepository
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        val context = androidx.compose.ui.platform.LocalContext.current
        val db = Room.databaseBuilder(context, SovereignDatabase::class.java, "lineage.db").build()
        val repository = SovereignRepository(db.sovereignDao())
        val viewModel: SovereignViewModel = viewModel(factory = SovereignViewModelFactory(repository))
        val chatViewModel: ChatViewModel = viewModel()
        
        AppNavigation(viewModel, chatViewModel)
      }
    }
  }
}
