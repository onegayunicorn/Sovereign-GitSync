package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.data.ConflictGene
import com.example.data.SovereignDatabase
import com.example.data.SovereignRepository
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        val context = LocalContext.current
        val db = Room.databaseBuilder(context, SovereignDatabase::class.java, "lineage.db").build()
        val repository = SovereignRepository(db.sovereignDao())
        val viewModel: SovereignViewModel = viewModel(factory = SovereignViewModelFactory(repository))
        val chatViewModel: ChatViewModel = viewModel()
        
        AppNavigation(viewModel, chatViewModel)
      }
    }
  }
}

@Composable
fun SovereignDashboard(viewModel: SovereignViewModel) {
  val genes by viewModel.conflictGenes.collectAsState()
  var selectedConflict by remember { mutableStateOf<ConflictGene?>(null) }
  
  if (selectedConflict != null) {
      ConflictDetailDialog(
          conflict = selectedConflict!!,
          onDismiss = { selectedConflict = null }
      )
  }
  
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    containerColor = Color(0xFF0A0A0F)
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .padding(16.dp)
        .fillMaxSize()
    ) {
      Text(
        text = "SOVEREIGN GITSYNC",
        style = MaterialTheme.typography.headlineSmall,
        color = Color(0xFF00D4FF)
      )
      Spacer(modifier = Modifier.height(16.dp))
      
      // Panel 1: Coherence
      Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFF1A1A25),
        shape = RoundedCornerShape(12.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(text = "SYSTEM COHERENCE", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
          Text(text = "0.9998 Ω", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        }
      }
      
      Spacer(modifier = Modifier.height(16.dp))
      
      // Panel 2: Conflicts
      Surface(
        modifier = Modifier.fillMaxWidth().weight(1f),
        color = Color(0xFF1A1A25),
        shape = RoundedCornerShape(12.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(text = "ACTIVE CONFLICTS", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
          if (genes.isEmpty()) {
            Text(text = "No conflicts detected.", color = Color.White, modifier = Modifier.padding(top = 8.dp))
          } else {
            LazyColumn {
                items(genes) { gene ->
                    Text(
                        text = gene.filePath,
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable { selectedConflict = gene }
                    )
                }
            }
          }
        }
      }
    }
  }
}

@Composable
fun ConflictDetailDialog(conflict: ConflictGene, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            color = Color(0xFF1A1A25),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "CONFLICT DETAIL", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                Text(text = "File: ${conflict.filePath}", color = Color.White)
                Text(text = "Risk Score: ${conflict.riskScore}", color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onDismiss) {
                    Text("Close")
                }
            }
        }
    }
}
