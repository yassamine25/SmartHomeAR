package com.example.ar.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.ar.R

import com.example.ar.ui.components.StepCard
import com.example.ar.ui.components.CategoryItem
import com.example.ar.ui.components.BottomMenu
import com.example.ar.ui.components.TopBar

@Composable
fun ScannerARScreen(
    onNavigateToAR: () -> Unit,
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

        // BACKGROUND
        Image(
            painter = painterResource(R.drawable.sofa),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.5f
        )

        // OVERLAY
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.35f))
        )

        // NAVBAR + TITLE
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.TopStart)
        ) {

            TopBar(
                onMenuClick = {
                    isMenuOpen = !isMenuOpen
                },
                onLogoClick = {
                    onNavigateHome()
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // BOUTON ANNULER
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF6C63FF))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Annuler",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    append("Scanner la pièce et ")
                    withStyle(style = SpanStyle(color = customBlue, fontWeight = FontWeight.ExtraBold)) {
                        append("placez")
                    }
                    append("\nvos meubles en ")
                    withStyle(style = SpanStyle(color = customBlue, fontWeight = FontWeight.Bold)) {
                        append("temps réel")
                    }
                },
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold
            )
        }

        // CENTRE (Steps + Detection Status)
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 160.dp)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                StepCard("1", "Scannez\nlentement", R.drawable.ic_camera)
                StepCard("2", "Détectez\nle sol", R.drawable.ic_cube)
                StepCard("3", "Placez\nvotre meuble", R.drawable.ic_heart)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(gradient)
                    .shadow(10.dp, RoundedCornerShape(30.dp))
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Text(
                    "Sol détecté\nChoisissez un meuble à placer",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White.copy(alpha = 0.2f))
                    .border(1.dp, Color.Blue.copy(alpha = 0.3f), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Surface détectée avec succès",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }

        // BAS (Product Selection + Final Button)
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 90.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .clip(RoundedCornerShape(40.dp))
                    .background(Color.White.copy(alpha = 0.2f))
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) { CategoryItem("", R.drawable.salon) }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) { CategoryItem("", R.drawable.chaise) }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) { CategoryItem("", R.drawable.table1) }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) { CategoryItem("", R.drawable.table2) }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(gradient)
                    .shadow(10.dp, RoundedCornerShape(30.dp))
                    .clickable { onNavigateToAR() }
                    .padding(horizontal = 24.dp, vertical = 10.dp)
            ) {
                Text("Placez ce meuble en AR →", color = Color.White)
            }
        }

        // MENU BURGER (Side Menu Overlay)
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
