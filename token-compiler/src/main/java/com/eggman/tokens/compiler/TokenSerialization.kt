package com.eggman.tokens.compiler

import com.eggman.tokens.model.token.ButtonJsonToken
import com.eggman.tokens.model.token.ColorTokenReference
import com.eggman.tokens.model.token.TypographyTokenReference

/**
 * A set of functions around serializing json tokens into kotlin code.
 */
internal object TokenSerialization {

    @Suppress("DefaultLocale")
    fun ColorTokenReference.constructor(): String {
        val alpha = alpha?.let { ", ${String.format("%.2f", it)}f"} ?: ""
        return "Color(Colors.${light.lastPart()}, Colors.${dark.lastPart()}$alpha)"
    }

    fun TypographyTokenReference.constructor(colorsKey: String): String {
        return "Typography(" +
                "${fontColor.colorOn(colorsKey)}, " +
                "FontFamily.${fontFamily.lastPart()}, " +
                "FontWeight.${fontWeight.lastPart()}, " +
                "FontSize.${size.lastPart()})"
    }

    private fun String.colorOn(colorsKey: String): String {
        return "$colorsKey.${lastPart()}"
    }

    private fun String.typographyOn(typographyKey: String): String {
        return "${typographyKey}.${lastPart()}"
    }

    private fun String.radiusToken(): String {
        return "Radius.${lastPart()}"
    }

    private fun String.elevationToken(): String {
        return "Elevation.${lastPart()}"
    }

    /**
     * This absolutely would be code generated, just saving time.
     */
    fun ButtonJsonToken.constructor(colorsKey: String, typographyKey: String): String {
        return "Button(" +
                "${containerColor.colorOn(colorsKey)}, " +
                "${contentColor.colorOn(colorsKey)}, " +
                "${disabledContainerColor.colorOn(colorsKey)}, " +
                "${disabledContentColor.colorOn(colorsKey)}, " +
                "${radius.radiusToken()}, " +
                "${elevation.elevationToken()}, " +
                "${textStyle.typographyOn(typographyKey)} " +
                ")"
    }

    private fun String.lastPart(): String {
        return splitToken().last()
    }

    private fun String.splitToken(): List<String>{
        return removePrefix("{").removeSuffix("}").split('.')
    }
}