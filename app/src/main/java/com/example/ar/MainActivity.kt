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
                        // --- 1. شاشات التسجيل ---
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
                            onNavigateToScanner = { currentScreen = "scanner" },
                            onNavigateHome = { },
                            onNavigateToProfile = { currentScreen = "profile" },
                            onNavigateToProjects = { currentScreen = "projects" },
                            onNavigateToCatalogue = { currentScreen = "catalogue" } // ✅ زدنا الربط للكاطالوج
                        )

                        "catalogue" -> CatalogueScreen( // ✅ زدنا حالة شاشة الكاطالوج
                            onNavigateHome = { currentScreen = "home" },
                            onNavigateToProfile = { currentScreen = "profile" },
                            onNavigateToProjects = { currentScreen = "projects" },
                            onNavigateToCatalogue = { }
                        )

                        "projects" -> ProjectsScreen(
                            onNavigateHome = { currentScreen = "home" }
                        )

                        "profile" -> ProfileScreen(
                            onNavigateHome = { currentScreen = "home" },
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