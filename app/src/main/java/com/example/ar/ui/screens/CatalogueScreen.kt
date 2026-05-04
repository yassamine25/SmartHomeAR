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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ar.R
import com.example.ar.ui.components.BottomMenu

// موديل البيانات
data class Product(val name: String, val price: String, val image: Int)

@Composable
fun CatalogueScreen(
    onNavigateHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToProjects: () -> Unit,
    onNavigateToCatalogue: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Salon") }
    val categories = listOf("Salon", "Chambre", "Cuisine", "Éclairage", "Table", "Tapis")

    val products = listOf(
        Product("Chaise", "1200 DH", R.drawable.chaise),
        Product("Table 1", "2500 DH", R.drawable.table1),
        Product("Table 2", "3000 DH", R.drawable.table2),
        Product("Canapé", "12000 DH", R.drawable.sofa), // استعملت sofa حيت ديجا عندك
        Product("Miroir", "750 DH", R.drawable.salon)   // استعملت salon حيت ديجا عندك
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 أحسن للقراءة، أو 3 كيفما بغيتي
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header
            item(span = { GridItemSpan(2) }) {
                Column {
                    Text(
                        text = "Catalogue AR",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                        color = Color(0xFF1A0BE9)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // البحث
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Rechercher...") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(30.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // التصنيفات
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

            // المنتجات
            gridItems(products) { product ->
                ProductCardItem(product)
            }
        }

        // المنيو اللي لتحت
        BottomMenu(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.9f)
                .padding(bottom = 20.dp),
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