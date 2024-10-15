package com.sagar.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.sagar.designsystem.atomic.CustomColors
import com.sagar.designsystem.atomic.*

@Composable
fun ZomatoCloneTheme(
    content: @Composable () -> Unit
) {
    val customColors = CustomColors(
        primarySurfaceWhite = primarySurfaceWhite,
        primarySurfaceBlack = primarySurfaceBlack,
        greenSubdued = greenSubdued,
        green = green,
        yellowSubdued = yellowSubdued,
        red = red,
        redSubdued = redSubdued,
        primaryText = primaryText,
        secondaryText = secondaryText,
        tertiaryText = tertiaryText,
        textBlack = textBlack,
        textWhite = textWhite,
        primaryDivider = primaryDivider,
        secondaryDivider = secondaryDivider,
        tertiaryDivider = tertiaryDivider,
        brown = brown,
        iconSecondary = iconSecondary
    )

    val customTypography = CustomTypography(
        heading = heading,
        bodyMedium = bodyMedium,
        bodyLarge = bodyLarge,
        titleMedium = titleMedium,
        body = body,
        label = label,
        bodySemibold = bodySemibold,
        titleLarge = titleLarge,
        bodySemiboldLarger = bodySemiboldLarger
    )

    val customElevation = CustomElevation(
        default = default, pressed = pressed
    )

    val customShape = CustomShape(
        labelsRadius = labelsRadius,
        labelsRadiusSmall = labelsRadiusSmall,
        labelsRadiusLarge = labelsRadiusLarge,
        card = card,
        cardLarge = cardLarge
    )

    CompositionLocalProvider(
        LocalCustomColors provides customColors,
        LocalCustomTypography provides customTypography,
        LocalCustomElevation provides customElevation,
        LocalCustomShape provides customShape,
        content = content
    )
}

object CustomTheme {
    val colors: CustomColors
        @Composable
        get() = LocalCustomColors.current
    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current
    val elevation: CustomElevation
        @Composable
        get() = LocalCustomElevation.current
    val shape: CustomShape
        @Composable
        get() = LocalCustomShape.current
}