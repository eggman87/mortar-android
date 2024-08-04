package com.eggman.mortar.android.components.atoms

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.eggman.mortar.android.Mortar
import com.eggman.mortar.android.MortarComposeButtonStyle

/**
 * Primary action button. This button is meant for cases where the CTA being performed by the user
 * is the primary action when considering all the actions they can take in a given section of the
 * interface.
 *
 * This wraps [Button], see the docs for this class for more information.
 *
 * Note: if we were generating a full theme, things like shape, content padding, style would be
 * configurable on this api
 *
 * @param modifier the Modifier to be applied to this button
 * @param isEnabled controls the enabled state of this button. When false, this component will not
 * respond to user input, and it will appear visually disabled and disabled to accessibility services.
 * @param style the mortar style to be applied to this button. Defaults to semantic tokens based
 * on primary colors.
 * @param label label of the text to show in the button.
 * @param onClick called when this button is clicked
 * @param isLoading if the button is in a loading state (disabled and loading indicator shown).
 * @param contentDescription the content description for this button, used for accessibility.
 */
@Composable
fun MortarButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: MortarComposeButtonStyle = MortarComposeButtonStyle(
      containerColor = Mortar.colors.primaryContainer,
        contentColor = Mortar.colors.onPrimaryContainer,
        disabledContainerColor = Mortar.colors.disabledPrimary,
        disabledContentColor = Mortar.colors.disabledOnPrimary,
        radius = 8.dp, //if we added sizes/shapes to theme, default would be driven from that. Not doing for time reasons.
        elevation = 2.dp, //if we added sizes/shapes to theme, default would be driven from that. Not doing for time reasons.
        textStyle = Mortar.typography.bodyMedium
    ),
    isLoading: Boolean = false,
    isEnabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Filled,
    contentDescription: String = label
) {

    /**
     * Note: We are setting content description for accessibility. The API for this recommends we only
     * set it for things like images, icons, etc...but I wanted to show it being used.
     */
    val currentContentDescription = when {
        isLoading -> "Loading, $contentDescription"
        !isEnabled -> "Disabled, $contentDescription"
        else -> contentDescription
    }

    // this is a little more complex than I like, but is due to supporting every variant in one api.
    val textColor = if (variant == ButtonVariant.Outlined || variant == ButtonVariant.Text) {
        if (isEnabled || isLoading) {
            Mortar.colors.onSurface
        } else {
            style.disabledContainerColor
        }
    } else {
        if (isEnabled || isLoading) {
            style.contentColor
        } else {
            style.disabledContentColor
        }
    }

    val buttonContent: @Composable RowScope.() -> Unit = {
        ButtonContent(
            label = label,
            isLoading = isLoading,
            buttonTextStyle = style.textStyle.copy(color = textColor),
            textColor = textColor
        )
    }

    val modifierToUse = Modifier
        .semantics { this.contentDescription = currentContentDescription }
        .focusable(enabled = isEnabled)
        .then(modifier)

    val shape = RoundedCornerShape(style.radius)
    // block click events when the button is in loading state...
    val localClickHandler: () -> Unit = {
        if (!isLoading) {
            onClick()
        }
    }

    when (variant) {
        ButtonVariant.Elevated -> {
            ElevatedButton(
                modifier = modifierToUse,
                onClick = localClickHandler,
                enabled = isEnabled,
                shape = shape,
                elevation = ButtonDefaults.buttonElevation(defaultElevation = style.elevation),
                colors = ButtonDefaults.buttonColors().copy(
                    contentColor = style.contentColor,
                    containerColor = style.containerColor,
                    disabledContentColor = style.disabledContentColor,
                    disabledContainerColor = style.disabledContainerColor,
                ),
                content = buttonContent
            )
        }
        ButtonVariant.Filled -> {
            Button(
                modifier = modifier,
                onClick = localClickHandler,
                enabled = isEnabled,
                shape = shape,
                elevation = ButtonDefaults.buttonElevation(defaultElevation = style.elevation),
                colors = ButtonDefaults.buttonColors().copy(
                    contentColor = style.contentColor,
                    containerColor = style.containerColor,
                    disabledContentColor = style.disabledContentColor,
                    disabledContainerColor = style.disabledContainerColor,
                ),
                content = buttonContent
            )
        }
        ButtonVariant.Tonal -> {
            FilledTonalButton(
                modifier = modifierToUse,
                onClick = localClickHandler,
                enabled = isEnabled,
                shape = shape,
                colors = ButtonDefaults.filledTonalButtonColors().copy(
                    contentColor = Mortar.colors.onSecondaryContainer,
                    containerColor = Mortar.colors.secondaryContainer,
                ),
                content = buttonContent
            )
        }
        ButtonVariant.Outlined -> {
            OutlinedButton(
                modifier = modifierToUse,
                onClick = localClickHandler,
                enabled = isEnabled,
                shape = shape,
                colors = ButtonDefaults.outlinedButtonColors().copy(
                    contentColor = Mortar.colors.onSurface
                ),
                content = buttonContent
            )
        }
        ButtonVariant.Text -> {
            TextButton(
                modifier = modifierToUse,
                onClick = localClickHandler,
                enabled = isEnabled,
                shape = shape,
                content = buttonContent
            )
        }
    }
}

@Composable
private fun ButtonContent(label: String,
                          isLoading: Boolean,
                          buttonTextStyle: TextStyle,
                          textColor: Color) {
    if (isLoading) {
        // we use sub compose layout to measure what button size is when not loading content,
        // so that changing loading state maintains button size.
        val localDensity = LocalDensity.current
        SubcomposeLayout { constraints ->
            val text = subcompose("text") {
                Text(text = label, style = buttonTextStyle)
            }.first().measure(constraints = constraints)

            val row = subcompose("loadingRow") {
                Row(horizontalArrangement = Arrangement.Center) {
                    CircularProgressIndicator(
                        strokeWidth = 1.dp,
                        modifier = Modifier.size((text.height/localDensity.density).dp),
                        color = textColor
                    )
                }
            }.first().measure(Constraints.fixed(text.width, text.height))
            layout(width = text.width, row.height) {
                row.placeRelative(0, 0)
            }
        }
    } else {
        Text(text = label, style = buttonTextStyle)
    }
}

/**
 * The kind of button.
 */
enum class ButtonVariant {
    /**
     * Elevated buttons are high-emphasis buttons that are essentially FilledTonalButtons with a shadow.
     * To prevent shadow creep, only use them when absolutely necessary, such as when the button
     * requires visual separation from patterned container.
     */
    Elevated,

    /**
     * Filled buttons are high-emphasis buttons. Filled buttons have the most visual impact after the
     * FloatingActionButton, and should be used for important, final actions that complete a flow,
     * like "Save", "Join now", or "Confirm".
     */
    Filled,

    /**
     * Filled tonal buttons are medium-emphasis buttons that is an alternative middle ground between
     * default Buttons (filled) and OutlinedButtons. They can be used in contexts where lower-priority
     * button requires slightly more emphasis than an outline would give, such as "Next" in an onboarding
     * flow. Tonal buttons use the secondary color mapping.
     */
    Tonal,

    /**
     * Outlined buttons are medium-emphasis buttons. They contain actions that are important, but are
     * not the primary action in an app. Outlined buttons pair well with Buttons to indicate an
     * alternative, secondary action.
     */
    Outlined,

    /**
     * Text buttons are typically used for less-pronounced actions, including those located in dialogs
     * and cards. In cards, text buttons help maintain an emphasis on card content. Text buttons are
     * used for the lowest priority actions, especially when presenting multiple options.
     */
    Text
}
