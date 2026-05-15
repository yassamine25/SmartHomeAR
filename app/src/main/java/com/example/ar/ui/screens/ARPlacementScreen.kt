package com.example.ar.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.geometry.Offset
import com.example.ar.R
import com.example.ar.ui.components.TopBar
import com.example.ar.ui.components.BottomMenu

import com.example.ar.data.Project
import com.example.ar.data.RetrofitInstance
import kotlinx.coroutines.launch

import android.content.Intent
import androidx.compose.ui.platform.LocalContext

@Composable
fun ARPlacementScreen(
    currentScreen: String,
    userId: Long?,
    onNavigateHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToProjects: () -> Unit,
    onNavigateToCatalogue: () -> Unit,
    onLogout: () -> Unit
) {
    var isMenuOpen by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    var message by remember { mutableStateOf("") }

    val context = LocalContext.current
    val activeColor = Color(0xFF1A0BE9)
    val inactiveColor = Color.Gray

    val customBlue = Color(0xFF1A0BE9)
    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFF1A0BE9), Color(0xFF6C63FF))
    )

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.sofa),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.9f
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            TopBar(
                onMenuClick = { isMenuOpen = !isMenuOpen },
                onLogoClick = { onNavigateHome() }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .clickable {
                        context.startActivity(
                            Intent(context, RealARActivity::class.java)
                        )
                    }
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text("Tester vrai AR", color = customBlue)
            }








            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(20.dp))
                    .background(gradient)
                    .clickable {
                        scope.launch {
                            if (userId != null) {
                                try {
                                    RetrofitInstance.projectApi.createProject(
                                        Project(
                                            name = "Nouveau placement AR",
                                            description = "Sauvegardé depuis AR",
                                            userId = userId,
                                            image = "salon"
                                        )
                                    )
                                    message = "Projet sauvegardé"
                                    onNavigateToProjects()
                                } catch (e: Exception) {
                                    message = "Erreur sauvegarde"
                                    e.printStackTrace()
                                }
                            }
                        }
                    }
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text("Valider", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Ajustez votre meuble et\nvalidez son placement",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(200.dp)
                .align(Alignment.Center)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val stroke = 6f
                drawLine(customBlue, Offset(0f, 0f), Offset(size.width, 0f), stroke)
                drawLine(customBlue, Offset(0f, size.height), Offset(size.width, size.height), stroke)
                drawLine(customBlue, Offset(0f, 0f), Offset(0f, size.height), stroke)
                drawLine(customBlue, Offset(size.width, 0f), Offset(size.width, size.height), stroke)
            }

            val points = listOf(
                Alignment.TopStart,
                Alignment.TopEnd,
                Alignment.BottomStart,
                Alignment.BottomEnd
            )

            points.forEach {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .align(it)
                )
            }
        }

        if (isMenuOpen) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f)).clickable { isMenuOpen = false })

            Column(modifier = Modifier.fillMaxHeight().width(220.dp).background(Color.White).padding(20.dp)) {
                Text("SmartHome", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = customBlue)
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    "Accueil",
                    color = if (currentScreen == "home") activeColor else inactiveColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isMenuOpen = false
                            onNavigateHome()
                        }
                        .padding(12.dp)
                )

                Text(
                    "Catalogue",
                    color = if (currentScreen == "catalogue") activeColor else inactiveColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth().clickable {
                        isMenuOpen = false
                        onNavigateToCatalogue()
                    }.padding(12.dp)
                )

                Text(
                    "Mes Projets",
                    color = if (currentScreen == "projects") activeColor else inactiveColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth().clickable {
                        isMenuOpen = false
                        onNavigateToProjects()
                    }.padding(12.dp)
                )

                Text(
                    "Profil",
                    color = if (currentScreen == "profile") activeColor else inactiveColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth().clickable {
                        isMenuOpen = false
                        onNavigateToProfile()
                    }.padding(12.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    "Se déconnecter",
                    color = Color.Red,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isMenuOpen = false
                            onLogout()
                        }
                        .padding(12.dp)
                )
            }
        }

        BottomMenu(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.9f),
            selectedItem = -1,
            onNavigateHome = onNavigateHome,
            onNavigateToProfile = onNavigateToProfile,
            onNavigateToProjects = onNavigateToProjects,
            onNavigateToCatalogue = onNavigateToCatalogue
        )
    }
}