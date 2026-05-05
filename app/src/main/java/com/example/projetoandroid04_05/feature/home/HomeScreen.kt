package com.example.projetoandroid04_05.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onOpenProfile: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tela principal") },
                actions = {
                    TextButton(onClick = onOpenProfile) {
                        Text("Perfil")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Bem vindo ao app autenticado!",
                style = MaterialTheme.typography.headlineSmall
            )
            Text("Essa tela so pode ser acessada depois que o usuario fez login")

            ElevatedCard {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Token JWT ativo",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "As próximas requisicoes enviam automaticamente o header com o token"
                    )
                }
            }
        }







    }
}