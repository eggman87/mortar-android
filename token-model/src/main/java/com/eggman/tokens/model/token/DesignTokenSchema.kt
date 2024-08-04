package com.eggman.tokens.model.token

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Design tokens for a given design token source. These tokens are managed in a central location,
 * often updated by designers. We download these tokens and parse through them to generate theme
 * code to be used in our android Mortar implementation.
 *
 * There are 3 types of tokens, [primitive], [semantic], and [component]. See their property descriptions
 * for more info on them.
 *
 * All tokens eventually will reduce down to a [primitive] token, which points to a raw design value
 * such as a color, or a size.
 *
 * @param primitive a collection of "primitive" design tokens. These tokens are the lowest level
 * of a design token and are meant to provide raw values. ex. white=#FFF
 * @param semantic a collection of "semantic" design tokens. These tokens describe more about intended
 * use and usually will point to a [primitive] token. These are usually high level theme tokens that
 * are not specific to any one component (think primaryContainer in M3).
 * @param component a collection of "component" design tokens. These tokens describe specific design
 * properties of specific components. These tokens can reference [semantic] or [primitive] tokens.
 * o
 */
@Serializable
data class DesignTokens(
    val primitive: PrimitiveJsonTokens,
    val semantic: SemanticJsonTokens,
    val component: Map<String, ComponentJsonToken>
)

/**
 * A collection of design tokens representing raw primitive tokens.
 */
@Serializable
data class PrimitiveJsonTokens(
    val colors: Map<String, String>,
    val radius: Map<String, Int>,
    val elevation: Map<String, Int>,
    val motion: Map<String, Int>,
    val fontNames: Map<String, String>,
    val fontSizes: Map<String, Int>,
    val fontWeights: Map<String, Int>
)

/**
 * A collection of design tokens representing semantic tokens.
 */
@Serializable
data class SemanticJsonTokens(
    val colors: Map<String, ColorTokenReference>,
    val radius: Map<String, String>,
    val elevation: Map<String, String>,
    val typography: Map<String, TypographyTokenReference>
)

/**
 * Custom component definitions that have unique schemas agreed upon before adding support for
 * component to project.
 */
@Serializable
sealed class ComponentJsonToken


/**
 * A custom component definition for buttons.
 */
@Serializable
@SerialName("button")
data class ButtonJsonToken(
    val containerColor: String,
    val contentColor: String,
    val disabledContainerColor: String,
    val disabledContentColor: String,
    val radius: String,
    val elevation: String,
    val textStyle: String
) : ComponentJsonToken()

/**
 * Represents a reference to a color token, which contains a set of token references for each
 * property of a color.
 *
 * @param light color to be shown when system is in light mode.
 * @param dark color to be shown when system is in dark mode.
 * @param alpha alpha transparency level.
 */
@Serializable
data class ColorTokenReference(val light: String, val dark: String, val alpha: Double? = null)

/**
 * Represents a reference to a typography token, which itself is a set of token references for
 * each property of a typography style.
 */
@Serializable
data class TypographyTokenReference(
    val fontColor: String,
    val fontFamily: String,
    val fontWeight: String,
    val size: String
)