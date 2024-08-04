package com.eggman.mortar.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalMortarTypography = staticCompositionLocalOf<MortarComposeTypography>{
    // in a real app we would probably default this to a base theme
    error("Mortar theme not configured!")
}

val LocalMortarColorScheme = staticCompositionLocalOf<MortarComposeColors> {
    // in a real app we would probably default this to a base theme
    error("Mortar theme not configured!")
}

val LocalMortarComponents = staticCompositionLocalOf<MortarComposeComponents> {
    // in a real app we would probably default this to a base theme
    error("Mortar theme not configured!")
}

object Mortar {
    val typography: MortarComposeTypography
        @Composable
        get() = LocalMortarTypography.current

    val colors: MortarComposeColors
        @Composable
        get() = LocalMortarColorScheme.current

    val components: MortarComposeComponents
        @Composable
        get() = LocalMortarComponents.current
}
