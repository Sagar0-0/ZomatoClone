package com.sagar.designsystem.atomic

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

@Immutable
data class CustomTypography(
    val heading: TextStyle,
    val bodyMedium: TextStyle,
    val bodyLarge: TextStyle,
    val titleMedium: TextStyle,
    val body: TextStyle,
    val label: TextStyle,
    val bodySemibold: TextStyle,
    val titleLarge: TextStyle,
    val bodySemiboldLarger: TextStyle,
)

val LocalCustomTypography = staticCompositionLocalOf {
    CustomTypography(
        TextStyle.Default,
        TextStyle.Default,
        TextStyle.Default,
        TextStyle.Default,
        TextStyle.Default,
        TextStyle.Default,
        TextStyle.Default,
        TextStyle.Default,
        TextStyle.Default,
    )
}