package com.example.zomatoclone.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zomatoclone.ui.theme.CustomTheme
import com.example.zomatoclone.ui.theme.ZomatoCloneTheme

const val TWEEN_SPEED = 650
private val primaryColor: Color = Color(0xFFECECEC)
private val shadeColor: Color = Color(0xFFC7C7C7)

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun Preview() {
    ZomatoCloneTheme {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppShimmerHeader()
            Spacer(modifier = Modifier.weight(1f))
            AppShimmerText(modifier = Modifier.padding(horizontal = 25.dp))
            Height(value = 15)
            AppLoader()
            Spacer(modifier = Modifier.weight(0.7f))
            AppShimmerBottom()
        }
    }
}

@Composable
fun AppShimmerBottom(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        repeat(4) {
            Column {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CustomTheme.shape.labelsRadiusLarge)
                        .shimmerEffect()
                )
                Height(value = 5)

                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .width(35.dp)
                        .clip(CustomTheme.shape.card)
                        .shimmerEffect()
                )
            }
        }
    }
}

@Composable
fun AppShimmerHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Row {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth(0.6f)
                        .clip(CustomTheme.shape.labelsRadiusLarge)
                        .shimmerEffect()
                )

                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth(0.9f)
                        .clip(CustomTheme.shape.labelsRadiusLarge)
                        .shimmerEffect()
                )

                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth(0.7f)
                        .clip(CustomTheme.shape.labelsRadiusLarge)
                        .shimmerEffect()
                )
            }

            Width(value = 20)

            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CustomTheme.shape.labelsRadiusLarge)
                    .shimmerEffect()
            )
            Width(value = 10)

            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CustomTheme.shape.labelsRadiusLarge)
                    .shimmerEffect()
            )
        }

        Height(value = 15)
        Box(
            modifier = Modifier
                .height(35.dp)
                .fillMaxWidth()
                .clip(CustomTheme.shape.labelsRadiusLarge)
                .shimmerEffect()
        )
    }
}

@Composable
fun AppLoader(
    modifier: Modifier = Modifier,
    radius: Dp = 20.dp,
    color: Color = CustomTheme.colors.red,
    strokeWidth: Dp = 2.dp,
    strokeCap: StrokeCap = StrokeCap.Round,
    cycleDuration: Int = 1300
) {
    val infiniteTransition = rememberInfiniteTransition("")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = cycleDuration,
                easing = LinearEasing
            ),
        ),
        label = ""
    )
    val rotationReverse by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = cycleDuration,
                easing = LinearEasing
            ),
        ),
        label = ""
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(radius + radius / 2) // Set the size of the canvas based on the arc's radius
                .rotate(rotation) // Apply the rotation animation
        ) {
            drawArc(
                color = color,
                startAngle = -180f, // Start drawing the arc from the left
                sweepAngle = 300f, // Draw a half-circle arc
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = strokeCap)
            )
        }
        Canvas(
            modifier = Modifier
                .size(radius) // Set the size of the canvas based on the arc's radius
                .rotate(rotationReverse) // Apply the rotation animation
        ) {
            drawArc(
                color = color,
                startAngle = -180f, // Start drawing the arc from the left
                sweepAngle = 100f, // Draw a half-circle arc
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = strokeCap)
            )
        }
    }

}

@Composable
fun AppShimmerText(
    modifier: Modifier = Modifier,
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = size.width.toFloat() * -1.5f,
        targetValue = size.width.toFloat() * 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(TWEEN_SPEED)
        ), label = ""
    )
    val brush = Brush.linearGradient(
        colors = listOf(
            primaryColor,
            shadeColor,
            primaryColor,
        ),
        start = Offset(startOffsetX, 0f),
        end = Offset(startOffsetX + size.width.toFloat(), 0f)
    )
    Text(
        modifier = modifier
//            .graphicsLayer(alpha = 0.99f)
//            .drawWithCache {
//                onDrawWithContent {
//                    drawContent()
//                    drawRect(brush, blendMode = BlendMode.SrcAtop)
//                }
//            }
            .onGloballyPositioned {
                size = it.size
            },
        text = "We deliver pies, but on reaching you they become cutie pies!",
        style = CustomTheme.typography.bodyMedium.copy(brush = brush, fontSize = 16.sp),
        color = CustomTheme.colors.textBlack,
        textAlign = TextAlign.Center
    )
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = size.width.toFloat() * -1.5f,
        targetValue = size.width.toFloat() * 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(TWEEN_SPEED)
        ), label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                primaryColor,
                shadeColor,
                primaryColor
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat() / 2)
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}
