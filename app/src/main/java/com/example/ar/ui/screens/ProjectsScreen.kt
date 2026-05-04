package com.example.ar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ar.R
import com.example.ar.ui.components.*
import com.example.ar.ui.components.TopBar


data class Project(
    val title: String,
    val date: String,
    val image: Int
)

@Composable
fun ProjectsScreen(
    currentScreen: String,
    onNavigateHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToProjects: () -> Unit,
    onNavigateToCatalogue: () -> Unit,
    onLogout: () -> Unit
) {

    var isMenuOpen by remember { mutableStateOf(false) }
    val customBlue = Color(0xFF1A0BE9)
    val activeColor = Color(0xFF1A0BE9)
    val inactiveColor = Color.Gray

    // LISTE DES PROJETS (EN DEHORS DU LazyColumn)
    val projects = listOf(
        Project("Salon Moderne", "12/04/2024", R.drawable.salon),
        Project("Chambre classique", "05/04/2024", R.drawable.chambre),
        Project("Bureau", "28/03/2024", R.drawable.bureau)
    )

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {

            // TOP BAR
            TopBar(
                onMenuClick = { isMenuOpen = !isMenuOpen },
                onLogoClick = { onNavigateHome() }
            )

            // TITLE
            Text(
                text = "Mes Projets AR",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )

            // LISTE
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 20.dp)
            ) {

                item {
                    Text(
                        text = "Retrouvez ici vos designs sauvegardés",
                        fontSize = 14.sp,
                        color = GrayText,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                }

                items(projects) { project ->
                    ProjectCard(
                        title = project.title,
                        date = project.date,
                        imageRes = project.image
                    )
                }
            }
        }


        if (isMenuOpen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .clickable { isMenuOpen = false }
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(220.dp)
                    .background(Color.White)
                    .padding(20.dp)
            ) {

                Text(
                    "SmartHome",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = customBlue
                )

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
            selectedItem = 3,
            onNavigateHome = onNavigateHome,
            onNavigateToProfile = onNavigateToProfile,
            onNavigateToProjects = onNavigateToProjects,
            onNavigateToCatalogue = onNavigateToCatalogue
        )
    }
}