package com.eggman.mortar.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import com.eggman.mortar.android.ComposeMapper.toColor
import com.eggman.mortar.android.ComposeMapper.toCompose
import com.eggman.tokens.model.ThemeColors
import com.eggman.tokens.model.MortarTheme

import com.eggman.tokens.model.ThemeTypography

@Stable
object MortarThemes {

    /**
     * A Mortar theme that adapts Mortar styles into a material 3 theme definition. This allows
     * most material components to be styled using Mortar styles by default.
     *
     * You can access [MortarComposeTheme] components by accessing local compositions that are
     * referencing them.
     *
     * See [LocalMortarColorScheme], [LocalMortarTypography], and [LocalMortarComponents]
     *
     * @param theme the [MortarTheme] instance to use for the compose theme.
     * @param isInDarkTheme should the theme be in dark mode or not.
     * @param content the content of your UI.
     */
    @Composable
    fun MaterialMortarTheme(
        theme: MortarTheme,
        isInDarkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
        CompositionLocalProvider(
            LocalMortarTypography provides theme.typography.toCompose(isInDarkTheme),
            LocalMortarColorScheme provides theme.colors.toCompose(isInDarkTheme),
            LocalMortarComponents provides theme.components.toCompose(isInDarkTheme)
        ) {
            MaterialTheme(
                colorScheme = theme.colors.materialColors(isInDarkTheme),
                typography = theme.typography.materialTypography(isInDarkTheme),
                content = {
                    // makes [bodyMedium] the default text...compared to large.
                    ProvideTextStyle(value = Mortar.typography.bodyMedium) {
                        content()
                    }
                }
            )
        }
    }

    /**
     * Adopts a [MortarTheme.typography] into M3 [Typography].
     */
    @Composable
    private fun ThemeTypography.materialTypography(isDark: Boolean): Typography {
        return MaterialTheme.typography.copy(
            bodySmall = bodySmall.toCompose(isDark),
            bodyMedium = bodyMedium.toCompose(isDark),
            bodyLarge = bodyLarge.toCompose(isDark)
        )
    }

    /**
     * Adopts a [MortarTheme.colors] into M3 [ColorScheme]
     */
    @Composable
    private fun ThemeColors.materialColors(isDark: Boolean): ColorScheme {
        return MaterialTheme.colorScheme.copy(
            primary = primary.toColor(isDark),
            onPrimary = onPrimary.toColor(isDark),
            primaryContainer = primaryContainer.toColor(isDark),
            onPrimaryContainer = onPrimaryContainer.toColor(isDark),
            secondary = secondary.toColor(isDark),
            onSecondary = onSecondary.toColor(isDark),
            secondaryContainer = secondaryContainer.toColor(isDark),
            onSecondaryContainer = onSecondaryContainer.toColor(isDark),
            tertiary = secondary.toColor(isDark),
            onTertiary = secondary.toColor(isDark),
            tertiaryContainer = secondary.toColor(isDark),
            onTertiaryContainer = secondary.toColor(isDark),
            error = error.toColor(isDark),
            onError = onError.toColor(isDark),
            errorContainer = errorContainer.toColor(isDark),
            onErrorContainer = onErrorContainer.toColor(isDark),
            background = background.toColor(isDark),
            onBackground = onBackground.toColor(isDark),
            surface = surface.toColor(isDark),
            onSurface = onSurface.toColor(isDark),
            surfaceVariant = surface.toColor(isDark),
            onSurfaceVariant = onSurface.toColor(isDark),
            surfaceTint = surface.toColor(isDark)
        )
    }
}

