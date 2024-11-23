package com.example.gs2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Cores claras
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1565C0), // Azul vibrante
    secondary = Color(0xFF4CAF50), // Verde vibrante
    tertiary = Color(0xFFFFC107), // Amarelo solar
    background = Color(0xFFE3F2FD), // Azul claro
    surface = Color.White, // Branco para superfícies
    onPrimary = Color.White, // Texto em botões primários
    onSecondary = Color.White, // Texto em botões secundários
    onBackground = Color.Black, // Texto em fundo claro
    onSurface = Color.Black // Texto em superfícies claras
)

// Cores escuras
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF90CAF9), // Azul claro no modo escuro
    secondary = Color(0xFFA5D6A7), // Verde claro no modo escuro
    tertiary = Color(0xFFFFF176), // Amarelo suave no modo escuro
    background = Color(0xFF121212), // Preto para fundo escuro
    surface = Color(0xFF1E1E1E), // Cinza escuro para superfícies
    onPrimary = Color.Black, // Texto em botões primários escuros
    onSecondary = Color.Black, // Texto em botões secundários escuros
    onBackground = Color.White, // Texto em fundo escuro
    onSurface = Color.White // Texto em superfícies escuras
)

@Composable
fun GS2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
