@file:Suppress("unused")

package com.eggman.mortar.tokens

import com.eggman.tokens.model.token.GenerateTokens

/**
 * The below objects will have code generated for each object as a new theme. The token-compiler will
 * do all the work to generate the theme.
 *
 * Themes generated will be named ${ThemePrefix}Mortar.kt
 */
@GenerateTokens(
    themePrefix = "Heb",
    jsonFile = "heb_tokens.json",
)
internal object HebTokenSchema

@GenerateTokens(
    themePrefix = "Cm",
    jsonFile = "cm_tokens.json"
)
internal object CentralMarketTokenSchema
