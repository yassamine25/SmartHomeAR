package com.example.ar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.ar.ui.screens.*
import com.example.ar.ui.components.LightBg

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = LightBg) {

                    var currentScreen by rememberSaveable { mutableStateOf("signin") }
                    var userPhone by rememberSaveable { mutableStateOf("") }
                    var connectedUserId by rememberSaveable { mutableStateOf<Long?>(null) }

                    when (currentScreen) {
                        "signin" -> SignInScreen(
                            onGoToSignUp = { currentScreen = "signup" },
                            onLoginSuccess = { userId ->
                                connectedUserId = userId
                                currentScreen = "home"
                            }
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
                            userId = connectedUserId,
                            currentScreen = "projects",
                            onNavigateHome = { currentScreen = "home" },
                            onNavigateToProfile = { currentScreen = "profile" },
                            onNavigateToProjects = { },
                            onNavigateToCatalogue = { currentScreen = "catalogue" },
                            onLogout = {
                                connectedUserId = null
                                currentScreen = "signin"
                            }
                        )
                        "profile" -> ProfileScreen(
                            userId = connectedUserId,
                            currentScreen = "profile",
                            onNavigateHome = { currentScreen = "home" },
                            onNavigateToProfile = { },
                            onNavigateToProjects = { currentScreen = "projects" },
                            onNavigateToCatalogue = { currentScreen = "catalogue" },
                            onLogout = {
                                connectedUserId = null
                                currentScreen = "signin"
                            }
                        )
                        "scanner" -> ScannerARScreen(
                            currentScreen = "scanner",
                            onNavigateToAR = { currentScreen = "ar" },
                            onNavigateHome = { currentScreen = "home" },
                            onNavigateToProfile = { currentScreen = "profile" },
                            onNavigateToProjects = { currentScreen = "projects" },
                            onNavigateToCatalogue = { currentScreen = "catalogue" },
                            onLogout = {
                                connectedUserId = null
                                currentScreen = "signin"
                            }
                        )
                        "ar" -> ARPlacementScreen(
                            currentScreen = "ar",
                            userId = connectedUserId,
                            onNavigateHome = { currentScreen = "home" },
                            onNavigateToProfile = { currentScreen = "profile" },
                            onNavigateToProjects = { currentScreen = "projects" },
                            onNavigateToCatalogue = { currentScreen = "catalogue" },
                            onLogout = {
                                connectedUserId = null
                                currentScreen = "signin"
                            }
                        )
                    }
                }
            }
        }
    }
}
