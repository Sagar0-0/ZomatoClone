package com.sagar.designsystem.atomic

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColors(
    val primarySurfaceWhite: Color,
    val primarySurfaceBlack: Color,
    val greenSubdued: Color,
    val green: Color,
    val yellowSubdued: Color,
    val red: Color,
    val redSubdued: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val tertiaryText: Color,
    val textBlack: Color,
    val textWhite: Color,
    val primaryDivider: Color,
    val secondaryDivider: Color,
    val tertiaryDivider: Color,
    val brown: Color,
    val iconSecondary: Color,
)

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
    )
}