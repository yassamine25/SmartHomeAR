package com.example.ar.ui.screens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.ar.R
import com.example.ar.ui.components.BottomMenu
import com.example.ar.ui.components.CategoryItem
import com.example.ar.ui.components.StepCard
import com.example.ar.ui.components.TopBar

@Composable
fun ScannerARScreen(
    currentScreen: String,
    onNavigateToAR: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToProjects: () -> Unit,
    onNavigateToCatalogue: () -> Unit,
    onLogout: () -> Unit
) {
    var isMenuOpen by remember { mutableStateOf(false) }
    val customBlue = Color(0xFF1A0BE9)
    val inactiveColor = Color.Gray
    val activeColor = Color(0xFF1A0BE9)
    val context = LocalContext.current
    val lifecycleOwner = LocalContext.current as LifecycleOwner


    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    DisposableEffect(Unit) {
        onDispose {
            try {
                val cameraProvider = cameraProviderFuture.get()
                cameraProvider.unbindAll() // Libère la caméra pour l'AR
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFF1A0BE9), Color(0xFF6C63FF))
    )

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
    }

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (!hasCameraPermission) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Veuillez autoriser la caméra",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx)
                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()
                        val preview = Preview.Builder().build()
                        preview.setSurfaceProvider(previewView.surfaceProvider)
                        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }, ContextCompat.getMainExecutor(ctx))
                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )

            // UI Layer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.TopStart)
            ) {
                TopBar(
                    onMenuClick = { isMenuOpen = !isMenuOpen },
                    onLogoClick = { onNavigateHome() }
                )
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF6C63FF))
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                        .clickable { onNavigateHome() }
                ) {
                    Text("Annuler", color = Color.White, fontSize = 12.sp)
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
                    Box(Modifier.weight(1f), contentAlignment = Alignment.Center) { CategoryItem("", R.drawable.salon) }
                    Box(Modifier.weight(1f), contentAlignment = Alignment.Center) { CategoryItem("", R.drawable.chaise) }
                    Box(Modifier.weight(1f), contentAlignment = Alignment.Center) { CategoryItem("", R.drawable.table1) }
                    Box(Modifier.weight(1f), contentAlignment = Alignment.Center) { CategoryItem("", R.drawable.table2) }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(gradient)
                        .shadow(10.dp, RoundedCornerShape(30.dp))
                        .clickable {
                            onNavigateToAR() // Va vers ARPlacementScreen
                        }
                        .padding(horizontal = 24.dp, vertical = 10.dp)
                ) {
                    Text("Placez ce meuble en AR →", color = Color.White)
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
                        color = if (currentScreen == "projects") activeColor else inactiveColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isMenuOpen = false
                                onNavigateToProjects()
                            }
                            .padding(12.dp)
                    )

                    Text(
                        "Profil",
                        color = if (currentScreen == "profile") activeColor else inactiveColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isMenuOpen = false
                                onNavigateToProfile()
                            }
                            .padding(12.dp)
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
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(0.9f),
                selectedItem = -1,
                onNavigateHome = onNavigateHome,
                onNavigateToProfile = onNavigateToProfile,
                onNavigateToProjects = onNavigateToProjects,
                onNavigateToCatalogue = onNavigateToCatalogue
            )
        }
    }
}
