package com.eggman.tokens.model.component

import com.eggman.tokens.model.Color

/**
 * A button definition that represents a custom component style definition. Typically
 * these would not include
 */
data class Button(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val radius: Int,
    val elevation: Int,
    val textStyle: com.eggman.tokens.model.Typography
)