package com.example.ar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ar.ui.components.*

@Composable
fun ProfileScreen(
    onNavigateHome: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBg)
            .padding(horizontal = 24.dp)
    ) {
        // 1. Top Bar (زر الرجوع)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateHome) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MainBlack)
            }
            Text(
                "Mon Profil",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MainBlack
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 2. عرض معلومات المستخدم فقط ✅
        UserInfoCard(
            name = "AR scanner",
            email = "scanner.ar@design.studio",
            phone = "+212 600 000 000"
        )

        Spacer(modifier = Modifier.weight(1f)) // دفع الزر للأسفل

        // 3. زر تسجيل الخروج
        SimpleLogoutButton(onClick = onLogout)

        Spacer(modifier = Modifier.height(40.dp))
    }
}