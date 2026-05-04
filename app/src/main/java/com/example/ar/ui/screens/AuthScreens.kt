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
        AppHeader(title = "Connectez")
        Spacer(modifier = Modifier.height(40.dp))
        CustomCard {
            CustomInput(label = "Email ou numéro du téléphone", value = email, onValueChange = { email = it })
            Spacer(modifier = Modifier.height(16.dp))
            CustomInput(label = "Mot de passe", value = pass, onValueChange = { pass = it }, isPassword = true)
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(text = "Continue", onClick = onLoginSuccess)
            Spacer(modifier = Modifier.height(24.dp))
            NavigationLink(text1 = "vous n'avez pas de compte? ", text2 = "Inscrivez_Vous", onClick = onGoToSignUp)
        }
    }
}

@Composable
fun SignUpScreen(
    onRegisterSuccess: (String) -> Unit,
    onBackToSignIn: () -> Unit
) {
    var nom by remember { mutableStateOf("") }
    var prenom by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AppHeader(title = "Inscrivez")
        Spacer(modifier = Modifier.height(40.dp))

        CustomCard {

            CustomInput(
                label = "Nom",
                value = nom,
                onValueChange = { nom = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomInput(
                label = "Prénom",
                value = prenom,
                onValueChange = { prenom = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomInput(
                label = "Date de naissance",
                value = date,
                onValueChange = { date = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomInput(
                label = "Email ou numéro du téléphone",
                value = phone,
                onValueChange = { phone = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = "Continuer",
                onClick = {
                    if (phone.isNotEmpty()) {
                        onRegisterSuccess(phone)
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            NavigationLink(
                text1 = "Vous avez déjà un compte ? ",
                text2 = "Se connecter",
                onClick = onBackToSignIn
            )
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