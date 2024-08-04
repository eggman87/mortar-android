package com.eggman.tokens.model.token

/**
 * Annotation used to indicate that you want to generate design tokens in the project and class where you
 * apply this annotation. When this is detected and `tokens-compiler` is in your classpath, design
 * system tokens will be generated.
 *
 * Ex of a generated theme token file will be [themePrefix]Mortar.kt
 *
 * @param themePrefix a prefix that will be appended to the files generated.
 * @param jsonFile the source json file you wish to generate from (in a real production version, this
 * json file would downloaded or maintained from another source but for now these json files live in
 * the processor module themselves).
 * @param packageName the package name you want the generated code to live in.
 */
annotation class GenerateTokens(
    val themePrefix: String,
    val jsonFile: String,
    val packageName: String = "com.mortar.android"
)
