package com.example.ar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// قطعة عرض معلومات المستخدم (صورة، اسم، إيميل)
@Composable
fun UserInfoCard(name: String, email: String, phone: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // دائرة الصورة الشخصية
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(SoftFieldBg), // اللون الرمادي الفاتح اللي عرفناه سابقا
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(60.dp),
                tint = GrayText
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // الاسم
        Text(
            text = name,
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MainBlack
        )

        Spacer(modifier = Modifier.height(8.dp))

        // الإيميل
        Text(
            text = email,
            fontSize = 16.sp,
            color = GrayText
        )

        Spacer(modifier = Modifier.height(4.dp))

        // رقم الهاتف (إضافي بما أننا في تطبيق AR يحتاج تواصل)
        Text(
            text = phone,
            fontSize = 14.sp,
            color = GrayText,
            fontWeight = FontWeight.Medium
        )
    }
}

// زر تسجيل الخروج بسيط في الأسفل
@Composable
fun SimpleLogoutButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEBEE)), // أحمر خفيف جدا
        shape = RoundedCornerShape(16.dp),
        elevation = null
    ) {
        Icon(Icons.Default.ExitToApp, contentDescription = null, tint = Color.Red)
        Spacer(modifier = Modifier.width(12.dp))
        Text("Se déconnecter", color = Color.Red, fontWeight = FontWeight.Bold)
    }
}