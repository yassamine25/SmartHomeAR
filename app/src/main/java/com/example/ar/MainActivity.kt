package com.example.ar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.ar.ui.screens.*
import com.example.ar.ui.components.LightBg

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = LightBg) {
                    var currentScreen by remember { mutableStateOf("signin") }
                    var userPhone by remember { mutableStateOf("") }

                    when (currentScreen) {

                        "signin" -> SignInScreen(
                            onGoToSignUp = { currentScreen = "signup" },
                            onLoginSuccess = { currentScreen = "home" }
                        )
                        "signup" -> SignUpScreen(
                            onRegisterSuccess = { phone -> userPhone = phone; currentScreen = "verification" },
                            onBackToSignIn = { currentScreen = "signin" }
                        )
                        "verification" -> VerificationScreen(
                            phoneNumber = userPhone,
                            onVerifySuccess = { currentScreen = "home" },
                            onBack = { currentScreen = "signup" }
                        )

                        // --- 2. الشاشات الرئيسية ---
                        "home" -> SmartHomeScreen(
                            currentScreen = "home",
                            onNavigateToScanner = { currentScreen = "scanner" },
                            onNavigateHome = { },
                            onNavigateToProfile = { currentScreen = "profile" },
                            onNavigateToProjects = { currentScreen = "projects" },
                            onNavigateToCatalogue = { currentScreen = "catalogue" },
                            onLogout = { currentScreen = "signin" }
                        )

                        "catalogue" -> CatalogueScreen(
                            currentScreen = "catalogue",
                            onNavigateHome = { currentScreen = "home" },
                            onNavigateToProfile = { currentScreen = "profile" },
                            onNavigateToProjects = { currentScreen = "projects" },
                            onNavigateToCatalogue = { },
                            onLogout = { currentScreen = "signin" },
                            onNavigateToAR = { currentScreen = "ar" }

                        )

                        "projects" -> ProjectsScreen(
                            currentScreen = "projects",
                            onNavigateHome = { currentScreen = "home" },
                            onNavigateToProfile = { currentScreen = "profile" },
                            onNavigateToProjects = { },
                            onNavigateToCatalogue = { currentScreen = "catalogue" },
                            onLogout = { currentScreen = "signin" }
                        )

                        "profile" -> ProfileScreen(
                            currentScreen = "profile",
                            onNavigateHome = { currentScreen = "home" },
                            onNavigateToProfile = { },
                            onNavigateToProjects = { currentScreen = "projects" },
                            onNavigateToCatalogue = {  currentScreen = "catalogue" },
                            onLogout = { currentScreen = "signin" }
                        )

                        "scanner" -> ScannerARScreen(
                            onNavigateToAR = { currentScreen = "ar" },
                            onNavigateHome = { currentScreen = "home" },
                            onNavigateToProfile = { currentScreen = "profile" },
                            onNavigateToProjects = { currentScreen = "projects" },
                            onNavigateToCatalogue = { currentScreen = "catalogue" }
                        )

                        "ar" -> ARPlacementScreen(
                            onNavigateHome = { currentScreen = "home" },
                            onNavigateToProfile = { currentScreen = "profile" },
                            onNavigateToProjects = { currentScreen = "projects" },
                            onNavigateToCatalogue = { currentScreen = "catalogue" }
                        )
                    }
                }
            }
        }
    }
}