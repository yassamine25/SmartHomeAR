package com.example.ar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ar.R

@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    onNavigateHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToProjects: () -> Unit,
    onNavigateToCatalogue: () -> Unit // 1. زدنا هاد الباراميتر هنا
){
    Box(
        modifier = modifier
            .padding(top = 2.dp)
            .shadow(12.dp, RoundedCornerShape(50.dp))
            .clip(RoundedCornerShape(50.dp))
            .background(Color(0xFFCFDEEC))
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        var selectedItem by remember { mutableIntStateOf(0) }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Home
            BottomItem("Accueil", R.drawable.ic_home, selectedItem == 0) {
                selectedItem = 0
                onNavigateHome()
            }

            // Search (ربطناه بالكاطالوج)
            BottomItem("Rechercher", R.drawable.ic_search, selectedItem == 1) {
                selectedItem = 1
                onNavigateToCatalogue() // 2. هنا كيتم الربط
            }

            // Profile
            BottomItem("Profil", R.drawable.ic_user, selectedItem == 2) {
                selectedItem = 2
                onNavigateToProfile()
            }

            // Projects
            BottomItem("Projets", R.drawable.ic_heart, selectedItem == 3) {
                selectedItem = 3
                onNavigateToProjects()
            }
        }
    }
}

@Composable
fun BottomItem(label: String, icon: Int, isSelected: Boolean, onClick: () -> Unit) {
    val color = if (isSelected) Color(0xFF1A0BE9) else Color.Gray
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(22.dp)
        )
        Text(text = label, fontSize = 10.sp, color = color)
    }
}