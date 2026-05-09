package com.example.ar.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.foundation.lazy.items as rowItems
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ar.R
import com.example.ar.ui.components.BottomMenu
import com.example.ar.ui.components.TopBar
import com.example.ar.ui.components.ProductCard

import com.example.ar.data.ApiProduct
import com.example.ar.data.RetrofitInstance
data class Product(val name: String, val price: String, val image: Int)

@Composable
fun CatalogueScreen(
    currentScreen: String,
    onNavigateHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToProjects: () -> Unit,
    onNavigateToCatalogue: () -> Unit,
    onLogout: () -> Unit,
    onNavigateToAR: () -> Unit

) {
    var isMenuOpen by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Salon") }
    val categories = listOf("Salon", "Chambre", "Cuisine", "Éclairage", "Table", "Tapis")
    val customBlue = Color(0xFF1A0BE9)
    val activeColor = Color(0xFF1A0BE9)
    val inactiveColor = Color.Gray

    var products by remember { mutableStateOf<List<ApiProduct>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            products = RetrofitInstance.api.getProducts()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header
            item(span = { GridItemSpan(2) }) {
                Column {
                    TopBar(
                        onMenuClick = { isMenuOpen = !isMenuOpen },
                        onLogoClick = { onNavigateHome() }
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Catalogue AR",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        fontSize = 20.sp,
                        color = Color(0xFF1A0BE9)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Recherche
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Rechercher...") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(30.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Catégories
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        rowItems(categories) { category ->
                            val isSelected = selectedCategory == category
                            Surface(
                                modifier = Modifier.clickable { selectedCategory = category },
                                shape = RoundedCornerShape(24.dp),
                                color = if (isSelected) Color(0xFF1A0BE9) else Color(0xFFF5F7FA)
                            ) {
                                Text(
                                    text = category,
                                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                                    color = if (isSelected) Color.White else Color.Gray,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            // Produits
            gridItems(products) { product ->
                ProductCard(
                    name = product.name,
                    price = "${product.price} DH",
                    image = R.drawable.sofa,
                    onClickAR = {
                        onNavigateToAR()
                    }
                )
            }
        }

        // Side Menu
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

        // Bottom Menu
        BottomMenu(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.9f),
            selectedItem = 1,
            onNavigateHome = onNavigateHome,
            onNavigateToProfile = onNavigateToProfile,
            onNavigateToProjects = onNavigateToProjects,
            onNavigateToCatalogue = onNavigateToCatalogue
        )
    }
}

@Composable
fun ProductCardItem(product: Product) {
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            Column(modifier = Modifier.padding(10.dp)) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(product.name, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(product.price, color = Color(0xFF1A0BE9), fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth().height(32.dp),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Voir AR", fontSize = 10.sp)
                }
            }
            IconButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier.align(Alignment.TopEnd).padding(4.dp).size(24.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isFavorite) Color.Red else Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
