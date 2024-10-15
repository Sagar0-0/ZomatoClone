package com.example.zomatoclone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.sagar.designsystem.ZomatoCloneTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview(backgroundColor = 0xFF000000, showBackground = true)
@Composable
private fun Preview() {
    ZomatoCloneTheme {
        val scope = rememberCoroutineScope()
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

        LaunchedEffect(Unit) {
            scope.launch {
                sheetState.expand()
            }
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    scope.launch {
                        sheetState.expand()
                    }
                }
        ) {
            ModalBottomSheet(
                sheetState = sheetState,
                shape = RectangleShape,
                onDismissRequest = {
                    scope.launch {
                        sheetState.hide()
                    }
                },
                containerColor = Color.Unspecified,
                contentColor = Color.Unspecified,
                dragHandle = null,
                scrimColor = Color.Black.copy(0.9f),
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    NeoPopBottomSheetContainer(
                        modifier = Modifier.padding(horizontal = 60.dp),
                        isDragHandleVisible = false
                    ) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxWidth()
                                .padding(vertical = 70.dp, horizontal = 40.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Sheet content")
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {
                Text("Screen content")
            }
        }

    }
}

@Composable
private fun NeoPopBottomSheetContainer(
    modifier: Modifier = Modifier,
    containerTopShadowHeight: Dp = 5.dp,
    containerColors: Brush = Brush.verticalGradient(
        listOf(
            Color.White,
            Color.Gray,
        )
    ),
    containerStrokeColors: Brush = Brush.sweepGradient(
        listOf(
            Color.LightGray,
            Color.Gray,
        )
    ),
    containerTopColors: Brush = Brush.sweepGradient(
        listOf(
            Color.LightGray,
            Color.Gray,
        )
    ),
    containerStrokeWidth: Dp = 0.dp,
    isDragHandleVisible: Boolean = true,
    dragHandleSize: DpSize = DpSize(60.dp, 10.dp),
    dragHandleTopPadding: Dp = 20.dp,
    dragHandleColors: Brush = Brush.verticalGradient(
        listOf(
            Color.White,
            Color.LightGray,
        )
    ),
    dragHandleShadowColors: Brush = Brush.sweepGradient(
        listOf(
            Color.LightGray,
            Color.Gray,
        )
    ),
    dragHandleStrokeColors: Brush = Brush.sweepGradient(
        listOf(
            Color.LightGray,
            Color.Gray,
        )
    ),
    dragHandleTopShadowHeight: Dp = 5.dp,
    dragHandleStrokeWidth: Dp = 0.2.dp,
    content: @Composable BoxScope.() -> Unit
) {
    var containerHeightAsPx by remember {
        mutableFloatStateOf(0f)
    }
    var containerWidthAsPx by remember {
        mutableFloatStateOf(0f)
    }

    Box(
        modifier = modifier
            .padding(top = containerTopShadowHeight)
            .onGloballyPositioned { coordinates ->
                // Set column height using the LayoutCoordinates
                containerHeightAsPx = coordinates.size.height.toFloat()
                containerWidthAsPx = coordinates.size.width.toFloat()
            }
            .drawWithCache {
                onDrawBehind {
                    val sideHeightAsPx = containerTopShadowHeight.toPx()

                    // Container TopView
                    drawRect(
                        brush = containerColors,
                        topLeft = Offset(x = 0f, y = 0f),
                        size = Size(width = containerWidthAsPx, height = containerHeightAsPx),
                    )

                    // TopView of the container (3D effect)
                    Path().apply {
                        moveTo(x = 0f, y = 0f)
                        lineTo(
                            x = sideHeightAsPx,
                            y = -sideHeightAsPx
                        )
                        lineTo(
                            x = containerWidthAsPx - sideHeightAsPx,
                            y = -sideHeightAsPx
                        )
                        lineTo(
                            x = containerWidthAsPx,
                            y = 0f
                        )
                        close()
                        drawPath(
                            path = this,
                            brush = containerTopColors
                        )
                    }

                    // Stroke in container TopView
                    drawLine(
                        brush = containerStrokeColors,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = containerWidthAsPx, y = 0f),
                        strokeWidth = containerStrokeWidth.toPx()
                    )
                    drawLine(
                        brush = containerStrokeColors,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = 0f, y = containerHeightAsPx),
                        strokeWidth = containerStrokeWidth.toPx()
                    )
                    drawLine(
                        brush = containerStrokeColors,
                        start = Offset(x = containerWidthAsPx, y = 0f),
                        end = Offset(x = containerWidthAsPx, y = containerHeightAsPx),
                        strokeWidth = containerStrokeWidth.toPx()
                    )

                }
            }
    ) {
        if (isDragHandleVisible) {
            Column {
                //Drag Handle
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = dragHandleTopPadding)
                        .size(dragHandleSize)
                        .drawWithCache {
                            onDrawBehind {
                                val sideHeightAsPx = dragHandleTopShadowHeight.toPx()
                                // Drag Handle TopView
                                drawRect(
                                    brush = dragHandleColors,
                                    topLeft = Offset(x = 0f, y = 0f),
                                    size = Size(
                                        width = dragHandleSize.width.toPx(),
                                        height = dragHandleSize.height.toPx()
                                    ),
                                )

                                // TopView of the Drag Handle (3D effect)
                                Path().apply {
                                    moveTo(x = 0f, y = 0f)
                                    lineTo(
                                        x = sideHeightAsPx,
                                        y = -sideHeightAsPx
                                    )
                                    lineTo(
                                        x = dragHandleSize.width.toPx() - sideHeightAsPx,
                                        y = -sideHeightAsPx
                                    )
                                    lineTo(
                                        x = dragHandleSize.width.toPx(),
                                        y = 0f
                                    )
                                    close()
                                    drawPath(
                                        path = this,
                                        brush = dragHandleShadowColors
                                    )
                                }

                                // Drag Handle Stroke
                                drawRect(
                                    brush = dragHandleStrokeColors,
                                    topLeft = Offset(x = 0f, y = 0f),
                                    size = Size(
                                        width = dragHandleSize.width.toPx(),
                                        height = dragHandleSize.height.toPx()
                                    ),
                                    style = Stroke(width = dragHandleStrokeWidth.toPx())
                                )
                            }
                        }
                )
                Box {
                    content()
                }
            }
        } else {
            content()
        }
    }
}
