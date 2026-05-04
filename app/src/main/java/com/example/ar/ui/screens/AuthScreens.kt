package com.example.ar.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
// استيراد المكونات والألوان من ملف Components
import com.example.ar.ui.components.*

@Composable
fun SignInScreen(onGoToSignUp: () -> Unit, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppHeader(title = "Sign In")
        Spacer(modifier = Modifier.height(40.dp))
        CustomCard {
            CustomInput(label = "Email Address", value = email, onValueChange = { email = it })
            Spacer(modifier = Modifier.height(16.dp))
            CustomInput(label = "Password", value = pass, onValueChange = { pass = it }, isPassword = true)
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(text = "Continue", onClick = onLoginSuccess)
            Spacer(modifier = Modifier.height(24.dp))
            NavigationLink(text1 = "Don't have an account? ", text2 = "Sign Up", onClick = onGoToSignUp)
        }
    }
}

@Composable
fun SignUpScreen(onRegisterSuccess: (String) -> Unit, onBackToSignIn: () -> Unit) {
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppHeader(title = "Sign Up")
        Spacer(modifier = Modifier.height(40.dp))
        CustomCard {
            CustomInput(label = "Phone number", value = phone, onValueChange = { phone = it })
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(text = "Continue", onClick = { if (phone.isNotEmpty()) onRegisterSuccess(phone) })
            Spacer(modifier = Modifier.height(24.dp))
            NavigationLink(text1 = "Already have an account? ", text2 = "Sign In", onClick = onBackToSignIn)
        }
    }
}

@Composable
fun VerificationScreen(phoneNumber: String, onVerifySuccess: () -> Unit, onBack: () -> Unit) {
    var code by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppHeader(title = "Verify")
        Spacer(modifier = Modifier.height(40.dp))
        CustomCard {
            // استعملنا MainBlack اللي كاين فـ Components
            Text(
                text = "Enter the code sent to you at $phoneNumber",
                color = MainBlack,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomInput(label = "Code", value = code, onValueChange = { code = it })
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Resend code",
                color = GrayText,
                fontSize = 13.sp,
                modifier = Modifier.clickable { }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Back",
                color = GrayText,
                fontSize = 13.sp,
                modifier = Modifier.clickable { onBack() }
            )
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(text = "Verify Now", onClick = onVerifySuccess)
        }
    }
}