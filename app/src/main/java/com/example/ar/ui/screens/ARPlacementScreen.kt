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
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ARPlacementScreen(
    onNavigateHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToProjects: () -> Unit,
    onNavigateToCatalogue: () -> Unit
) {

    var isMenuOpen by remember { mutableStateOf(false) }

    val customBlue = Color(0xFF1A0BE9)
    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFF1A0BE9), Color(0xFF6C63FF))
    )

    Box(modifier = Modifier.fillMaxSize()) {



        // OVERLAY
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
        )

        // CONTENU PRINCIPAL
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            // TOPBAR
            TopBar(
                onMenuClick = { isMenuOpen = !isMenuOpen },
                onLogoClick = { onNavigateHome() }
            )

            Spacer(modifier = Modifier.height(30.dp))

            // ANNULER + DÉPLACER
            Box(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .clip(RoundedCornerShape(20.dp))
                        .background(gradient)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text("Annuler", color = Color.White, fontSize = 12.sp)
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clip(RoundedCornerShape(20.dp))
                        .background(gradient)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text("Déplacer", color = Color.White, fontSize = 12.sp)
                }
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

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(20.dp))
                    .background(gradient)
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text("Valider", color = Color.White)
            }

            Spacer(modifier = Modifier.weight(1f))
        }



        // MENU BURGER
        if (isMenuOpen) {
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f)).clickable { isMenuOpen = false }
            )
            Column(
                modifier = Modifier.fillMaxHeight().width(220.dp).background(Color.White).padding(20.dp)
            ) {
                Text("SmartHome", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Text("Accueil", modifier = Modifier.fillMaxWidth().clickable { isMenuOpen = false; onNavigateHome() }.padding(10.dp))
                Text("Catalogue", modifier = Modifier.fillMaxWidth().clickable { isMenuOpen = false; onNavigateToCatalogue() }.padding(10.dp))

                Text(
                    "Mes Projets",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isMenuOpen = false
                            onNavigateToProjects()
                        }
                        .padding(10.dp)
                )

                Text(
                    "Profil",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isMenuOpen = false
                            onNavigateToProfile()
                        }
                        .padding(10.dp)
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
