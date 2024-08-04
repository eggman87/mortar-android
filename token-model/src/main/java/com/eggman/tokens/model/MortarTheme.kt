package com.eggman.tokens.model

import com.eggman.tokens.model.component.Button


/**
 * A [MortarTheme] is a theme that contains all [ThemeColors], [ThemeTypography] and [ThemeComponents]
 * for a given theme. These themes and their values contain all resolved values for things like
 * color, shape, size etc...
 *
 * None of these values are in any platform specific format yet (for example compose.Color). These
 * leave the themes open to use in other use cases such as KMP.
 */
data class MortarTheme(
    val colors: ThemeColors,
    val typography: ThemeTypography,
    val components: ThemeComponents
)

/**
 * A set of predefined "Mortar" theme colors for a given theme. Each [Color] represents a high level
 * theme construct of a color.
 *
 * As of right now, these closely match Material 3's color system for brevity of this exercise.
 */
interface ThemeColors {
    val primary: Color
    val onPrimary: Color
    val primaryContainer: Color
    val onPrimaryContainer: Color
    val secondary: Color
    val onSecondary: Color
    val secondaryContainer: Color
    val onSecondaryContainer: Color
    val background: Color
    val onBackground: Color
    val surface: Color
    val onSurface: Color
    val error: Color
    val onError: Color
    val errorContainer: Color
    val onErrorContainer: Color
    val disabledPrimary: Color
    val disabledOnPrimary: Color
    val disabledSecondary: Color
    val disabledOnSecondary: Color
}

/**
 * A set of predefined "Mortar" theme text styles for a given theme. Each [Typography]  value
 * represents a high level theme construct of a Typography.
 *
 * As of right now, these closely match Material 3's color system for brevity of this exercise.
 */
interface ThemeTypography {
    val bodySmall: Typography
    val bodyMedium: Typography
    val bodyLarge: Typography
}

/**
 * A set of predefined "Mortar" theme component styles for a given theme. Each component is of a
 * specific type of UI component that we specifically want to style. Typically you would not use this
 * for something like a simple button (instead, a standard button can probably just use semantic tokens
 * directly) but I wanted to showcase how component specific styles could work.
 *
 * Supported components see the component package:
 * [Button]
 */
interface ThemeComponents {
    val buttonPrimary: Button
    val buttonSecondary: Button
}

data class Color(
    val light: String,
    val dark: String,
    val alpha: Float = 1.0f
)

data class Typography(
    val fontColor: Color,
    val fontFamily: String,
    val fontWeight: Int,
    val fontSize: Int,
)



