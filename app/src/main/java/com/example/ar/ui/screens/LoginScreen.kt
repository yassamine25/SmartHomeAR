package com.example.ar.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onNavigateToSignUp: () -> Unit, onLoginSuccess: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bienvenue !", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
        Text("Connectez-vous pour continuer", color = Color.Gray)

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "", onValueChange = {},
            label = { Text("Mot de passe") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onLoginSuccess, modifier = Modifier.fillMaxWidth().height(50.dp)) {
            Text("Se connecter")
        }

        TextButton(onClick = onNavigateToSignUp) {
            Text("Vous n'avez pas de compte ? S'inscrire")
        }
    }
}