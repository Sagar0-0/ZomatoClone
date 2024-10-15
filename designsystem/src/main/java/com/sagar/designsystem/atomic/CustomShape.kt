package com.sagar.designsystem.atomic

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
data class CustomShape(
    val labelsRadius: RoundedCornerShape,
    val labelsRadiusSmall: RoundedCornerShape,
    val labelsRadiusLarge: RoundedCornerShape,
    val card: RoundedCornerShape,
    val cardLarge: RoundedCornerShape,
)

val LocalCustomShape = staticCompositionLocalOf {
    CustomShape(
        RoundedCornerShape(0),
        RoundedCornerShape(0),
        RoundedCornerShape(0),
        RoundedCornerShape(0),
        RoundedCornerShape(0),
    )
}