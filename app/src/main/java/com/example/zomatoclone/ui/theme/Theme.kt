package com.example.zomatoclone.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zomatoclone.R

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

@Immutable
data class CustomElevation(
    val default: Dp,
    val pressed: Dp
)

@Immutable
data class CustomShape(
    val labelsRadius: Shape,
    val labelsRadiusSmall: Shape,
    val labelsRadiusLarge: RoundedCornerShape,
    val card: RoundedCornerShape,
    val cardLarge: RoundedCornerShape,
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
val LocalCustomElevation = staticCompositionLocalOf {
    CustomElevation(
        default = Dp.Unspecified,
        pressed = Dp.Unspecified
    )
}
val LocalCustomShape = staticCompositionLocalOf {
    CustomShape(
        RoundedCornerShape(0),
        RoundedCornerShape(0),
        RoundedCornerShape(0),
        RoundedCornerShape(0),
        RoundedCornerShape(0),
    )
}

@Composable
fun ZomatoCloneTheme(
    content: @Composable () -> Unit
) {
    val customColors = CustomColors(
        primarySurfaceWhite = Color(0xFFFFFFFF),
        primarySurfaceBlack = Color(0xFF1C1C1C),
        greenSubdued = Color(0xFFF5FFF9),
        green = Color(0xFF257E3E),
        yellowSubdued = Color(0xFFFEFBEC),
        red = Color(0xFFF04F5F),
        redSubdued = Color(0xFFFFF6F7),
        primaryText = Color(0xFF2A3143),
        secondaryText = Color(0xFF586377),
        tertiaryText = Color(0xFF757B91),
        textBlack = Color(0xFF000000),
        textWhite = Color(0xFFFFFFFF),
        primaryDivider = Color(0xFFE6E9EE),
        secondaryDivider = Color(0xFFD1D5D8),
        tertiaryDivider = Color(0xFFF3F4F8),
        brown = Color(0xFF80530E),
        iconSecondary = Color(0xFF9197A7)
    )

    val customTypography = CustomTypography(
        heading = TextStyle(fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.metropolis_extrabold))),
        bodyMedium = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.metropolis_medium))),
        bodyLarge = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.metropolis_medium))),
        titleMedium = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.metropolis_medium))),
        body = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.metropolis_medium))),
        label = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.metropolis_semibold))),
        bodySemibold = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.metropolis_semibold))),
        titleLarge = TextStyle(fontSize = 22.sp, fontFamily = FontFamily(Font(R.font.metropolis_bold))),
        bodySemiboldLarger = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.metropolis_semibold)))
    )

    val customElevation = CustomElevation(
        default = 4.dp,
        pressed = 8.dp
    )

    val customShape = CustomShape(
        labelsRadius = RoundedCornerShape(6.dp),
        labelsRadiusSmall = RoundedCornerShape(4.dp),
        labelsRadiusLarge = RoundedCornerShape(8.dp),
        card = RoundedCornerShape(12.dp),
        cardLarge = RoundedCornerShape(18.dp),
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