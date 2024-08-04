import com.eggman.tokens.compiler.TokenSerialization
import com.eggman.tokens.model.token.ColorTokenReference
import com.eggman.tokens.model.token.TypographyTokenReference
import org.junit.Assert.assertEquals
import org.junit.Test

class TokenSerializationTest {

    @Test
    fun `ColorTokenReference constructor returns correct string without alpha`() {
        val colorTokenReference = ColorTokenReference(
            light = "lightColor",
            dark = "darkColor",
            alpha = null
        )

        val result = TokenSerialization.run { colorTokenReference.constructor() }
        assertEquals("Color(Colors.lightColor, Colors.darkColor)", result)
    }

    @Test
    fun `ColorTokenReference constructor returns correct string with alpha`() {
        val colorTokenReference = ColorTokenReference(
            light = "lightColor",
            dark = "darkColor",
            alpha = 0.5
        )

        val result = TokenSerialization.run { colorTokenReference.constructor() }
        assertEquals("Color(Colors.lightColor, Colors.darkColor, 0.50f)", result)
    }

    @Test
    fun `TypographyTokenReference constructor returns correct string`() {
        val typographyTokenReference = TypographyTokenReference(
            fontFamily = "Serif",
            fontWeight = "Bold",
            size = "16sp"
        )

        val result = TokenSerialization.run { typographyTokenReference.constructor() }
        assertEquals("Typography(FontFamily.Serif, FontWeight.Bold, FontSize.16sp)", result)
    }
}
