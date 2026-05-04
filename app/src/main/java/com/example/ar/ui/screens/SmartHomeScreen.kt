package com.example.ar.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.ar.R
import com.example.ar.ui.components.CategoryItem
import com.example.ar.ui.components.SectionTitle
import com.example.ar.ui.components.ProductCard
import com.example.ar.ui.components.BottomMenu
import androidx.compose.ui.draw.scale
import com.example.ar.ui.components.TopBar

@Composable
fun SmartHomeScreen(
    onNavigateToScanner: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToProjects: () -> Unit,
    onNavigateToCatalogue: () -> Unit // 1. زدنا هاد الطريق لصفحة الكاطالوج
){
    var isMenuOpen by remember { mutableStateOf(false) }

    val customBlue = Color(0xFF1A0BE9)
    val customLightBlue = Color(0xFF6C63FF)
    val gradient = Brush.horizontalGradient(listOf(customBlue, customLightBlue))

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp)
        ) {
            Box(modifier = Modifier.height(480.dp)) {
                Image(
                    painter = painterResource(R.drawable.sofa),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    alpha = 0.5f
                )

                Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
                    TopBar(
                        onMenuClick = { isMenuOpen = !isMenuOpen },
                        onLogoClick = { onNavigateHome() }
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(gradient)
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_star),
                                    contentDescription = "AR",
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Mode AR Actif", color = Color.White, fontSize = 12.sp)
                            }
                        }
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Visualisez votre", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                        Text(text = "futur intérieur en AR", color = customBlue, fontSize = 28.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

                        Spacer(modifier = Modifier.height(25.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(30.dp))
                                .background(gradient)
                                .clickable { onNavigateToScanner() }
                                .padding(horizontal = 32.dp, vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(painter = painterResource(R.drawable.ic_camera), contentDescription = "Scanner", tint = Color.White, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Scanner ma pièce", color = Color.White, fontWeight = FontWeight.Bold)
                                }
                                Text("Placez vos meubles en temps réel", color = Color.White, fontSize = 11.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            CategoryItem("Salon", R.drawable.sofa)
                            Box(modifier = Modifier.scale(1.15f)) { CategoryItem("Chambre", R.drawable.chambre) }
                            CategoryItem("Bureau", R.drawable.bureau)
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(horizontalArrangement = Arrangement.Center) {
                            repeat(5) { index ->
                                Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(if (index == 0) customBlue else Color.White))
                                if (index < 4) Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                SectionTitle("Nos recommandations")
                Text(text = "Voir plus", color = customBlue, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable {
                    onNavigateToCatalogue() // ربطنا "Voir plus" حتى هي بالكاطالوج
                })
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                ProductCard("chaise", "1500 DH", R.drawable.chaise)
                ProductCard("table en bois", "4000 DH", R.drawable.table1)
                ProductCard("Table marbre", "4500 DH", R.drawable.table2)
            }
        }

        // --- Drawer Menu (Side Menu) ---
        if (isMenuOpen) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f)).clickable { isMenuOpen = false })

            Column(modifier = Modifier.fillMaxHeight().width(220.dp).background(Color.White).padding(20.dp)) {
                Text("SmartHome", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = customBlue)
                Spacer(modifier = Modifier.height(30.dp))

                Text("Accueil", modifier = Modifier.fillMaxWidth().clickable { isMenuOpen = false; onNavigateHome() }.padding(12.dp))

                // 2. ربط زر "Catalogue" فـ المنيو الجانبي
                Text(
                    "Catalogue",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isMenuOpen = false
                            onNavigateToCatalogue()
                        }
                        .padding(12.dp)
                )

                Text(
                    "Mes Projets",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isMenuOpen = false
                            onNavigateToProjects()
                        }
                        .padding(12.dp)
                )

                Text("Profil", modifier = Modifier.fillMaxWidth().clickable { isMenuOpen = false; onNavigateToProfile() }.padding(12.dp))
            }
        }

        BottomMenu(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(0.9f).padding(bottom = 15.dp),
            onNavigateHome = onNavigateHome,
            onNavigateToProfile = onNavigateToProfile,
            onNavigateToProjects = onNavigateToProjects,
            onNavigateToCatalogue = onNavigateToCatalogue // 3. تمرير الأكشن للـ BottomMenu (البحث)
        )
    }
}