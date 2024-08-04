package com.eggman.mortar.android

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eggman.tokens.model.ThemeColors
import com.eggman.tokens.model.ThemeComponents
import com.eggman.tokens.model.ThemeTypography
import com.eggman.tokens.model.Typography
import com.eggman.tokens.model.component.Button
import com.eggman.tokens.model.Color as ColorToken

/**
 * A collection of mapping functions that will map Mortar raw values (which are platform/implementation
 * agnostic) into compose raw values.
 */
object ComposeMapper {

    /**
     * Converts a Mortar [ThemeColors] into compose [MortarComposeColors].
     */
    fun ThemeColors.toCompose(isDark: Boolean): MortarComposeColors {
        return MortarComposeColors(
            primary = primary.toColor(isDark),
            onPrimary = onPrimary.toColor(isDark),
            primaryContainer = primaryContainer.toColor(isDark),
            onPrimaryContainer = onPrimaryContainer.toColor(isDark),
            secondary = secondary.toColor(isDark),
            onSecondary = onSecondary.toColor(isDark),
            secondaryContainer = secondaryContainer.toColor(isDark),
            onSecondaryContainer = onSecondaryContainer.toColor(isDark),
            background = background.toColor(isDark),
            onBackground = onBackground.toColor(isDark),
            surface = surface.toColor(isDark),
            onSurface = onSurface.toColor(isDark),
            error = error.toColor(isDark),
            onError = onError.toColor(isDark),
            errorContainer = errorContainer.toColor(isDark),
            onErrorContainer = onErrorContainer.toColor(isDark),
            disabledPrimary = disabledPrimary.toColor(isDark),
            disabledOnPrimary = disabledOnPrimary.toColor(isDark)
        )
    }

    /**
     * Converts a Mortar [ThemeTypography] into compose [MortarComposeTypography].
     */
    fun ThemeTypography.toCompose(isDark: Boolean): MortarComposeTypography {
        return MortarComposeTypography(
            bodySmall = bodySmall.toCompose(isDark),
            bodyMedium = bodyMedium.toCompose(isDark),
            bodyLarge = bodyLarge.toCompose(isDark)
        )
    }

    /**
     * Converts a Mortar [ThemeComponents] into compose [MortarComposeComponents].
     */
    fun ThemeComponents.toCompose(isDark: Boolean): MortarComposeComponents {
        return MortarComposeComponents(
            buttonPrimary = buttonPrimary.toCompose(isDark),
            buttonSecondary = buttonSecondary.toCompose(isDark)
        )
    }

    /**
     * Converts a Mortar color token into compose [Color].
     */
    internal fun ColorToken.toColor(isDark: Boolean): Color {
        return if (isDark) {
            Color(dark.toCompose(alpha))
        } else {
            Color(light.toCompose(alpha))
        }
    }

    /**
     * Adapts a hex color code to a compose compatible [Int].
     */
    fun String.toCompose(alpha: Float = 1.0f): Int {
        val alphaInt = (alpha * 255).toInt().coerceIn(0, 255)
        val colorInt = this.removePrefix("#").toLong(16).toInt()
        return (alphaInt shl 24) or (colorInt and 0xFFFFFF)
    }

    /**
     * Converts a mortar [Typography] token into a compose compatible [TextStyle].
     */
    fun Typography.toCompose(isDark: Boolean): TextStyle {
        return TextStyle(
            color = this.fontColor.toColor(isDark),
            //we would actually need font files to support custom fonts, easy to add support though.
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight(fontWeight),
            fontSize = fontSize.sp
        )
    }

    /**
     * Converts a mortar [Button] into a [MortarComposeButtonStyle].
     */
    fun Button.toCompose(isDark: Boolean): MortarComposeButtonStyle {
        return MortarComposeButtonStyle(
            containerColor = containerColor.toColor(isDark),
            contentColor = contentColor.toColor(isDark),
            disabledContentColor = disabledContentColor.toColor(isDark),
            disabledContainerColor = disabledContainerColor.toColor(isDark),
            radius = radius.dp,
            elevation = elevation.dp,
            textStyle = textStyle.toCompose(isDark)
        )
    }
}