package com.example.zomatoclone.ui.components

import android.graphics.CornerPathEffect
import android.graphics.DiscretePathEffect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.toComposePathEffect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun GooeyEffectSample2() {

    val pathDynamic = remember { Path() } // Dynamic circle path
    val pathStatic = remember { Path() } // Static circles path

    var currentPosition1 by remember { mutableStateOf(Offset.Unspecified) } // for dynamic circle
    var currentPosition2 by remember { mutableStateOf(Offset.Unspecified) } // for small circle

    val segmentCount = 20 // segment means the gooey effect between two shapes
    val pathMeasure = remember {
        PathMeasure()
    } // for dynamic cicle

    var isBigMoving by remember {
        mutableStateOf(true)
    } // for changing the circle

    val modifier = Modifier
        .pointerInput(Unit) {
            detectDragGestures { change, _ ->
                if (isBigMoving) {
                    currentPosition1 = change.position
                } else {
                    currentPosition2 = change.position
                }
            }
        } // to detect current position of circle

    val paint = remember {
        Paint()
    } // not mandatory but just to add the shader colors

    var isPaintSetUp by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(modifier = modifier.weight(1f)) {
            val center = size.center

            val position1 = if (currentPosition1 == Offset.Unspecified) {
                center
            } else currentPosition1
            val position2 = if (currentPosition2 == Offset.Unspecified) {
                center
            } else currentPosition2

            pathDynamic.reset()
            pathDynamic.addOval(
                Rect(
                    center = position1,
                    radius = 150f
                )
            )

            pathStatic.reset()
            pathStatic.addOval(
                Rect(
                    center = position2,
                    radius = 100f
                )
            )

            pathMeasure.setPath(pathDynamic, true)

            val discretePathEffect = DiscretePathEffect(pathMeasure.length / segmentCount, 0f)
            val cornerPathEffect = CornerPathEffect(50f)

            val chainPathEffect = PathEffect.chainPathEffect(
                outer = cornerPathEffect.toComposePathEffect(),
                inner = discretePathEffect.toComposePathEffect()
            )

            if (!isPaintSetUp) {

                paint.shader = LinearGradientShader(
                    from = Offset.Zero,
                    to = Offset(size.width, size.height),
                    colors = listOf(
                        Color(0xFFFF3B3B),
                        Color(0xFF1E68E9)
                    ),
                    tileMode = TileMode.Clamp
                )
                isPaintSetUp = true
                paint.pathEffect = chainPathEffect
            }

            val newPath = Path.combine(PathOperation.Union, pathDynamic, pathStatic)

            with(drawContext.canvas) {
                this.drawPath(
                    newPath,
//                Paint().apply { pathEffect = chainPathEffect } // without colors
                    paint
                )
            }
        }

        Button(onClick = { isBigMoving = !isBigMoving }) {
            Text(text = "Change")
        }
    }
}