package com.example.ar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ar.R
import com.example.ar.ui.components.*

@Composable
fun ProjectsScreen(onNavigateHome: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(LightBg)) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateHome) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = MainBlack)
            }
            Text("Mes Projets AR", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        // قائمة المشاريع
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)
        ) {
            item {
                Text(
                    "Retrouvez ici vos designs sauvegardés",
                    fontSize = 14.sp,
                    color = GrayText,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
            // بيانات تجريبية
            items(listOf(
                Pair("Salon Moderne", "12/04/2024"),
                Pair("Chambre Zen", "05/04/2024"),
                Pair("Bureau Setup", "28/03/2024")
            )) { project ->
                ProjectCard(title = project.first, date = project.second, imageRes = R.drawable.sofa)
            }
        }
    }
}

