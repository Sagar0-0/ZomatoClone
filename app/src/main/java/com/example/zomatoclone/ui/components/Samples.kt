package com.example.zomatoclone.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.sagar.designsystem.ZomatoCloneTheme
import com.sagar.designsystem.utils.onClickNoRipple


@Preview(backgroundColor = 0xFF000000)
@Composable
private fun Preview() {
    ZomatoCloneTheme {
        Column(
            modifier = Modifier.padding(100.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            PanelsSlider()
            NeoPopToggleButtonHorizontal()
        }
    }
}

@Composable
fun NeoPopToggleButtonHorizontal(modifier: Modifier = Modifier) {
    val width = 100.dp
    val height = 60.dp
    val dropHeightAngle = 50f

    var isOn by remember {
        mutableStateOf(true)
    }
    val offButtonRotation by animateFloatAsState(
        targetValue = if (isOn) -dropHeightAngle else 0f, label = ""
    )
    val onButtonRotation by animateFloatAsState(
        targetValue = if (isOn) 0f else dropHeightAngle, label = ""
    )

    val topHeightAnimation by animateDpAsState(
        targetValue = if (isOn) height.times(0.6f) else 0.dp, label = ""
    )
    val topWidthAnimation by animateDpAsState(
        targetValue = if (isOn) width else 0.dp, label = ""
    )
    val bottomHeightAnimation by animateDpAsState(
        targetValue = if (!isOn) height.times(0.6f) else 0.dp, label = ""
    )

    val bottomWidthAnimation by animateDpAsState(
        targetValue = if (!isOn) width else 0.dp, label = ""
    )

    Box {
        Column(modifier = Modifier.onClickNoRipple {
            isOn = !isOn
        }) {
            // Top-level box
            Box {
                // Rotated box for content
                Box(
                    modifier = Modifier.graphicsLayer {
                        transformOrigin = TransformOrigin(.5f, 1f)
                        rotationX = offButtonRotation
                        if (!isOn) {
                            shadowElevation = 100f
                            spotShadowColor = Color.Red
                        }
                    }, contentAlignment = Alignment.Center
                ) {
                    // Actual content on the rotated
                    Box(
                        modifier = Modifier
                            .width(width)
                            .height(height)
                            .clip(RoundedCornerShape(topStart = 50f, topEnd = 50f))
                            .border(
                                1.dp, Color.White, RoundedCornerShape(topStart = 50f, topEnd = 50f)
                            )
                            .background(Color.Red), contentAlignment = Alignment.Center
                    ) {
                        Text(text = "OFF",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .graphicsLayer(alpha = 0.99f)
                                .drawWithCache {
                                    val brush = Brush.sweepGradient(
                                        if (!isOn) {
                                            listOf(
                                                Color.White,
                                                Color.White,
                                            )
                                        } else {
                                            listOf(
                                                Color.Gray,
                                                Color.LightGray,
                                                Color.Gray,
                                                Color.LightGray,
                                                Color.Gray,
                                            )
                                        }
                                    )
                                    onDrawWithContent {
                                        drawContent()
                                        drawRect(brush = brush, blendMode = BlendMode.SrcAtop)
                                    }
                                })
                    }
                }

                Box(modifier = Modifier
                    .align(Alignment.TopCenter)
                    .height(topHeightAnimation)
                    .width(topWidthAnimation)
                    .zIndex(-1f)
                    .graphicsLayer {
                        rotationX = 60f
                        transformOrigin = TransformOrigin(.5f, 0f)
                        shape = RoundedCornerShape(topStart = 25f, topEnd = 25f)
                        shadowElevation = 10f
                    }
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Gray,
                                Color.LightGray,
                                Color.Black.copy(0.6f)
                            )
                        )
                    ))
            }

            // Top-level box
            Box {
                // Rotated box for content
                Box(
                    modifier = Modifier.graphicsLayer {
                        transformOrigin = TransformOrigin(.5f, 0f)
                        rotationX = onButtonRotation
                        if (isOn) {
                            shadowElevation = 100f
                            spotShadowColor = Color.Green
                        }
                    }, contentAlignment = Alignment.Center
                ) {
                    // Actual content on the rotated
                    Box(
                        modifier = Modifier
                            .width(width)
                            .height(height)
                            .clip(RoundedCornerShape(bottomStart = 50f, bottomEnd = 50f))
                            .border(
                                1.dp,
                                Color.White,
                                RoundedCornerShape(bottomStart = 50f, bottomEnd = 50f)
                            )
                            .background(Color.Green), contentAlignment = Alignment.Center
                    ) {
                        Text(text = "ON",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .graphicsLayer(alpha = 0.99f)
                                .drawWithCache {
                                    val brush = Brush.sweepGradient(
                                        if (isOn) {
                                            listOf(
                                                Color.White,
                                                Color.White,
                                            )
                                        } else {
                                            listOf(
                                                Color.Gray,
                                                Color.LightGray,
                                                Color.Gray,
                                                Color.LightGray,
                                                Color.Gray,
                                            )
                                        }
                                    )
                                    onDrawWithContent {
                                        drawContent()
                                        drawRect(brush = brush, blendMode = BlendMode.SrcAtop)
                                    }
                                })
                    }
                }

                Box(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(bottomHeightAnimation)
                    .width(bottomWidthAnimation)
                    .zIndex(-1f)
                    .graphicsLayer {
                        rotationX = -60f
                        transformOrigin = TransformOrigin(.5f, 1f)
                        shape = RoundedCornerShape(bottomStart = 25f, bottomEnd = 25f)
                        shadowElevation = 10f
                    }
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Black.copy(0.6f),
                                Color.LightGray,
                                Color.Gray,
                            )
                        )
                    ))
            }
        }

    }
}

//@Composable
//fun Parallelogram(
//    width: Dp = 100.dp,
//    height: Dp = 50.dp,
//    angle: Float = 20f
//) {
//
////        modifier = Modifier
////            .padding(10.dp)
////            .height(height)
////            .width(width)
////            .drawWithCache {
////                onDrawBehind {
////                    val radians = Math.toRadians(dropHeightAngle.toDouble())
////
////                    val x1 = 0f
////                    val y1 = 0f
////                    val x2 = 0f
////                    val y2 = height.toPx()
////
////                    val x3 = x2 + (width.toPx() * cos(radians)).toFloat()
////                    val y3 = y2 + (height.toPx() * sin(radians)).toFloat()
////                    val x4 = x2 + (width.toPx() * cos(radians)).toFloat()
////                    val y4 = height.toPx() * sin(radians).toFloat()
////
////                    val path = Path()
////                    path.moveTo(x1, y1)
////                    path.lineTo(x2, y2)
////                    path.lineTo(x3, y3)
////                    path.lineTo(x4, y4)
////                    path.close()
////
////                    drawPath(
////                        path = path,
////                        color = Color.Green,
////                    )
////
////                    val sideView = -(width.toPx() * sin(radians).toFloat()).coerceAtMost(15f)
////                    val bottomPath = Path()
////                    bottomPath.moveTo(x3, y3)
////                    bottomPath.lineTo(sideView, y3)
////                    bottomPath.lineTo(x2, y2)
////                    bottomPath.close()
////
////                    drawPath(
////                        path = bottomPath,
////                        color = Color.Gray,
////                    )
////
////                    val rightPath = Path()
////                    rightPath.moveTo(x1, y1)
////                    rightPath.lineTo(
////                        sideView,
////                        height.toPx() * sin(radians).toFloat()
////                    )
////                    rightPath.lineTo(sideView, y3)
////                    rightPath.lineTo(x2, y2)
////                    rightPath.close()
////
////                    drawPath(
////                        path = rightPath,
////                        color = Color.DarkGray,
////                    )
////                }
////            }
//    val radians = angle * Math.PI / 180.0
//
//    Canvas(modifier = Modifier.size(width, height)) {
//        val x1 = 0f
//        val y1 = 0f
//        val x2 = 0f
//        val y2 = height.toPx()
//
//        val x3 = x2 + (width.toPx() * cos(radians))
//        val y3 = y2 + (height.toPx() * sin(radians))
//        val x4 = x2 + (width.toPx() * cos(radians))
//        val y4 = height.toPx() * sin(radians)
//
//        val path = Path()
//        path.moveTo(x1, y1)
//        path.lineTo(x2, y2)
//        path.lineTo(x3.toFloat(), y3.toFloat())
//        path.lineTo(x4.toFloat(), y4.toFloat())
//        path.close()
//
//        drawPath(
//            path = path,
//            color = Color.Green,
//        )
//    }
//}
//
//@Composable
//private fun Triangle(
//    height: Float,
//    width: Float,
//    angleB: Double,
//    color: Color = Color.Red
//) {
//    val sideAC = width * tan(Math.toRadians(angleB))
//    val sideBC = width * cos(Math.toRadians(angleB))
//
//    Canvas(modifier = Modifier.size(sideBC.dp, sideAC.dp)) {
//        val path = Path()
//        path.moveTo(0f, 0f)
//        path.lineTo(-width, 0f)
//        path.lineTo(-width, sideAC.toFloat())
//        path.close()
//
//        rotate(angleB.toFloat(), Offset(x = 0f, y = 0f)) {
//            drawPath(
//                path = path,
//                color = color,
//            )
//        }
//    }
//}

//@Composable
//fun PanelsSlider(modifier: Modifier = Modifier) {
//    val list = remember {
//        listOf(
//            R.drawable.wallpaper_1,
//            R.drawable.wallpaper_2,
//            R.drawable.wallpaper_3,
//        )
//    }
////    val pagerState = rememberPagerState {
////        3
////    }
////    LaunchedEffect(true) {
////        while (true) {
////            if (pagerState.currentPage == list.size - 1) {
////                pagerState.scrollToPage(0)
////            } else {
////                delay(1000)
////                pagerState.animateScrollToPage(
////                    (pagerState.currentPage + 1)
////                )
////            }
////        }
////    }
////    HorizontalPager(
////        state = pagerState,
////        userScrollEnabled = false,
////
////    ) {
////        Image(
////            modifier = Modifier.fillMaxWidth(),
////            painter = painterResource(id = list[it]),
////            contentDescription = null,
////            contentScale = ContentScale.Crop
////        )
////    }
//
//    var index by remember { mutableIntStateOf(0) }
//
//    LaunchedEffect(true) {
//        while (true) {
//            delay(3000)
//            index = (index + 1) % 3
//        }
//    }
//
//
//    AnimatedContent(
//        targetState = index,
//        transitionSpec = {
//            slideInHorizontally { it } togetherWith
//                    slideOutHorizontally { it }
//        }, label = ""
//    ) { targetIndex ->
//        Box(modifier = Modifier.fillMaxSize()) {
//            Image(
//                modifier = Modifier
//                    .align(Alignment.CenterEnd)
//                    .fillMaxWidth(0.4f)
//                    .fillMaxHeight()
//                    .blur(10.dp)
//                    .background(Color.Red.copy(0.5f)),
//                painter = painterResource(
//                    id = list[
//                        if (targetIndex == 0) {
//                            list.size - 1
//                        } else {
//                            targetIndex - 1
//                        }
//                    ]
//                ),
//                contentDescription = null,
//                contentScale = ContentScale.Crop
//            )
//            Image(
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .fillMaxWidth(0.4f)
//                    .fillMaxHeight(),
//                painter = painterResource(id = list[targetIndex]),
//                contentDescription = null,
//                contentScale = ContentScale.Crop
//            )
//            Image(
//                modifier = Modifier
//                    .align(Alignment.CenterStart)
//                    .fillMaxWidth(0.4f)
//                    .fillMaxHeight()
//                    .blur(10.dp)
//                    .background(Color.Yellow.copy(0.5f)),
//                painter = painterResource(id = list[(targetIndex + 1) % list.size]),
//                contentDescription = null,
//                contentScale = ContentScale.Crop
//            )
//        }
//    }
//}