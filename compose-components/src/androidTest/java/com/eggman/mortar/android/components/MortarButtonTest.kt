package com.eggman.mortar.android.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.eggman.mortar.android.MortarThemes.MaterialMortarTheme
import com.eggman.mortar.android.components.atoms.MortarButton
import com.mortar.android.HebMortar
import org.junit.Rule
import org.junit.Test

class MortarButtonTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun testButtonLabel() {
        composeTestRule.setContent {
            MaterialMortarTheme(theme = HebMortar.theme) {
                MortarButton(
                    label = "I am a button",
                    onClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText("I am a button")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testButtonClick() {
        var clicked = false

        composeTestRule.setContent {
            MaterialMortarTheme(theme = HebMortar.theme) {
                MortarButton(
                    label = "I am a button",
                    onClick = { clicked = true }
                )
            }
        }

        composeTestRule.onNodeWithText("I am a button")
            .performClick()

        assert(clicked)
    }

    @Test
    fun testButtonEnabledState() {
        composeTestRule.setContent {
            MaterialMortarTheme(theme = HebMortar.theme) {
                MortarButton(
                    label = "I am a button",
                    onClick = {},
                    isEnabled = false
                )
            }
        }

        composeTestRule.onNodeWithText("I am a button")
            .assertIsNotEnabled()
    }

    @Test
    fun testButtonEnabledStateEnabled() {
        composeTestRule.setContent {
            MaterialMortarTheme(theme = HebMortar.theme) {
                MortarButton(
                    label = "I am a button",
                    onClick = {},
                    isEnabled = true
                )
            }
        }

        composeTestRule.onNodeWithText("I am a button")
            .assertIsEnabled()
    }
}