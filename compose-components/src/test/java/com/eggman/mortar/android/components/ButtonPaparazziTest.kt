package com.eggman.mortar.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.cash.paparazzi.Paparazzi
import com.eggman.mortar.android.MortarThemes.MaterialMortarTheme
import com.eggman.mortar.android.components.atoms.MortarButton
import com.mortar.android.HebMortar
import org.junit.Rule
import org.junit.Test

class ButtonPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `default button in dark mode`() {
        paparazzi.snapshot {
            Button(isDarkMode = true)
        }
    }

    @Test
    fun `default button in light mode`() {
        paparazzi.snapshot {
            Button(isDarkMode = false)
        }
    }

    @Composable
    fun Button(isDarkMode: Boolean) {
        Scaffold {
            Column(
                modifier = Modifier
                    .background(if (isDarkMode) Color.Black else Color.White)
                    .verticalScroll(rememberScrollState())
                    .padding(it)
                    .fillMaxWidth()
            ) {
                MaterialMortarTheme(HebMortar.theme, isDarkMode) {
                    MortarButton(label = "Test Button", onClick = { })
                }
            }
        }
    }
}