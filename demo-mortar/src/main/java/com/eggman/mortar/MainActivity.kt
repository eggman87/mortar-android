package com.eggman.mortar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eggman.mortar.android.LocalMortarColorScheme
import com.eggman.mortar.android.LocalMortarTypography
import com.eggman.mortar.android.Mortar
import com.eggman.mortar.android.MortarThemes.MaterialMortarTheme
import com.eggman.mortar.android.components.atoms.ButtonVariant
import com.eggman.mortar.android.components.atoms.MortarButton
import com.eggman.tokens.model.MortarTheme
import com.mortar.android.CmMortar
import com.mortar.android.HebMortar

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemInDarkMode = isSystemInDarkTheme()
            var inDarkMode by remember(systemInDarkMode) {
                mutableStateOf(systemInDarkMode)
            }
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Mortar Design System") },
                        actions =  {
                            IconButton(onClick = { inDarkMode = !inDarkMode}) {
                                Icon(
                                    imageVector =Icons.Default.Build,
                                    contentDescription = if (inDarkMode) "Go to light mode" else "Go to dark mode"
                                )
                            }
                        })
                }
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(it)
                        .fillMaxWidth()
                ) {
                    ButtonForTheme(name = "HEB", theme = HebMortar.theme, inDarkMode)
                    ButtonForTheme(name = "Central Market", theme = CmMortar.theme, inDarkMode)
                }
            }

        }
    }
}

@Composable
fun ButtonForTheme(name: String, theme: MortarTheme, isDark: Boolean ) {
    MaterialMortarTheme(
        theme = theme,
        isInDarkTheme = isDark,
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(
                    color = LocalMortarColorScheme.current.surface.copy(
                        alpha = if (isDark) 0.8f else 0.2f
                    )
                )
                .padding(16.dp)
        ) {
            Text(
                text = "$name Theme"
            )
            Text(
                text = "Please note this color scheme is somewhat random...Sorry if it hurts the eyes...",
                style = LocalMortarTypography.current.bodySmall
            )

            Spacer(modifier = Modifier.height(24.dp))
            MortarButton(
                onClick = {},
                label = "Default Button"
            )
            MortarButton(
                style = Mortar.components.buttonPrimary,
                onClick = {},
                label = "Primary Button"
            )
            MortarButton(
                style = Mortar.components.buttonSecondary,
                onClick = {},
                label = "Secondary Button"
            )
            MortarButton(
                onClick = {},
                isEnabled = false,
                label = "Disabled Button"
            )

            var isLoading by rememberSaveable {
                mutableStateOf(false)
            }

            Row(modifier = Modifier
                .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically) {
                MortarButton(
                    isLoading = isLoading,
                    label = "Tap to Load",
                    onClick = { isLoading = !isLoading }
                )
                Text(text = "  isLoading:")
                Checkbox(checked = isLoading, onCheckedChange = { isLoading = !isLoading} )
            }
            MortarButton(
                label = "Elevated",
                onClick = { },
                variant = ButtonVariant.Elevated
            )
            MortarButton(
                label = "Tonal",
                onClick = { },
                variant = ButtonVariant.Tonal
            )
            MortarButton(
                label = "Outlined",
                onClick = {},
                variant = ButtonVariant.Outlined
            )
            MortarButton(label = "Text", onClick = {}, variant = ButtonVariant.Text)

            Button(onClick = {}) {
                Text(text = "M3 Default")
            }
            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "M3 default (bodySmall)",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "M3 default (bodyMedium)",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "M3 default (bodyLarge)",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

}