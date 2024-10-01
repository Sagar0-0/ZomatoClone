package com.example.zomatoclone.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zomatoclone.R
import com.example.zomatoclone.ui.theme.CustomTheme
import com.example.zomatoclone.ui.theme.ZomatoCloneTheme

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun Preview() {
    ZomatoCloneTheme {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                AppRestaurantCardItemSmall(
                    title = "Pulhad Pizza",
                    duration = DeliveryTimeDuration.Thirty
                ) {}
                AppRestaurantCardItemSmall(
                    title = "Pulhad Pizza",
                    duration = DeliveryTimeDuration.FiftyFive
                ) {}
                AppRestaurantCardItemSmall(
                    title = "Pulhad Pizza",
                    duration = DeliveryTimeDuration.FortyFive
                ) {}
            }

            AppRestaurantCardItemLarge(
                title = "Pulhad Pizza",
                duration = DeliveryTimeDuration.FortyFive
            ) {}
            AppRestaurantCardItemLarge(
                title = "Pulhad Pizza",
                duration = DeliveryTimeDuration.FortyFive
            ) {}

        }
    }
}

@Composable
fun AppRestaurantCardItemSmall(
    title: String = "Pulhad Pizza",
    duration: DeliveryTimeDuration = DeliveryTimeDuration.Thirty,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.onClick { onClick() },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box {
            Image(
                modifier = Modifier
                    .width(116.dp)
                    .height(90.dp)
                    .clip(CustomTheme.shape.card),
                painter = painterResource(id = R.drawable.pizza_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Icon(
                modifier = Modifier
                    .padding(5.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd),
                painter = painterResource(id = R.drawable.heart), contentDescription = null,
                tint = Color.Unspecified
            )
        }
        Text(
            text = title,
            style = CustomTheme.typography.body,
            color = CustomTheme.colors.primaryText
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(13.5.dp),
                painter = painterResource(id = duration.iconRes),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Width(4)
            Text(
                text = duration.title,
                style = CustomTheme.typography.bodyMedium,
                color = CustomTheme.colors.secondaryText
            )
        }
        AppLabelNearFast()
    }
}

@Composable
fun AppRestaurantCardItemLarge(
    title: String = "Pulhad Pizza",
    duration: DeliveryTimeDuration = DeliveryTimeDuration.FiftyFive,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Shadowed(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CustomTheme.shape.cardLarge)
                .background(CustomTheme.colors.primarySurfaceWhite)
                .onClick { onClick() },
        ) {
            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(16.dp),
                    painter = painterResource(id = R.drawable.type_award),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Width(value = 8)
                Text(
                    text = "Best in Pizza - Restaurant Awards 2024",
                    style = CustomTheme.typography.label,
                    color = CustomTheme.colors.primaryText
                )
            }
            //image
            Box {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(
                            CustomTheme.shape.cardLarge.copy(
                                bottomEnd = CornerSize(0.dp),
                                bottomStart = CornerSize(0.dp)
                            )
                        ),
                    painter = painterResource(id = R.drawable.pizza_placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CustomTheme.shape.labelsRadiusSmall)
                            .background(CustomTheme.colors.primarySurfaceBlack.copy(0.7f))
                            .border(
                                width = 1.dp,
                                color = CustomTheme.colors.primarySurfaceBlack.copy(0.4f),
                                shape = CustomTheme.shape.labelsRadiusSmall
                            )
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "Paneer Lababdar . $430",
                            style = CustomTheme.typography.bodySemibold,
                            color = CustomTheme.colors.textWhite
                        )
                    }

                    Width(value = 5)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(id = R.drawable.heart), contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Width(value = 8)
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(id = R.drawable.type_hide),
                        contentDescription = null,
                        tint = CustomTheme.colors.primarySurfaceWhite
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(topEnd = 8.dp))
                            .background(CustomTheme.colors.primarySurfaceWhite)
                            .padding(start = 12.dp, top = 3.dp, end = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(13.5.dp),
                                painter = painterResource(id = duration.iconRes),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                            Width(4)
                            Text(
                                text = duration.title,
                                style = CustomTheme.typography.bodySemibold.copy(fontSize = 12.sp),
                                color = CustomTheme.colors.primaryText
                            )
                        }
                        AppDotDivider()
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "1.5 km",
                                style = CustomTheme.typography.bodySemibold.copy(fontSize = 12.sp),
                                color = CustomTheme.colors.primaryText
                            )
                        }
                        AppDotDivider()
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(13.5.dp),
                                painter = painterResource(id = R.drawable.type_scooter),
                                contentDescription = null,
                                tint = CustomTheme.colors.brown
                            )
                            Width(4)
                            Text(
                                text = "Free",
                                style = CustomTheme.typography.bodySemibold.copy(fontSize = 12.sp),
                                color = CustomTheme.colors.brown
                            )
                        }
                    }
                    RoundedCornerCanvas(size = 12.dp)
                }
            }

            //title section
            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = title,
                        style = CustomTheme.typography.titleLarge,
                        color = CustomTheme.colors.primaryText
                    )
                    Width(value = 10)
                    AppRatingLabel(rating = 4.3)
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "North Indian",
                        style = CustomTheme.typography.bodySemibold,
                        color = CustomTheme.colors.tertiaryText
                    )
                    AppDotDivider()
                    Text(
                        text = "Mughlai",
                        style = CustomTheme.typography.bodySemibold,
                        color = CustomTheme.colors.tertiaryText
                    )
                    AppDotDivider()
                    Text(
                        text = "$350 for one",
                        style = CustomTheme.typography.bodySemibold,
                        color = CustomTheme.colors.tertiaryText
                    )
                }

            }

            //Bottom section
            DashedGrayLine(modifier = Modifier.padding(horizontal = 12.dp))
            Height(value = 10)
            Row(
                modifier = Modifier.padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.type_premium_offer),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Width(value = 6)
                Text(
                    text = "Extra 10% OFF",
                    color = CustomTheme.colors.brown,
                    style = CustomTheme.typography.bodySemibold.copy(fontSize = 12.sp)
                )
            }
            Height(value = 10)

        }
    }
}

@Composable
fun DashedGrayLine(
    modifier: Modifier = Modifier,
    color: Color = CustomTheme.colors.secondaryDivider
) {
    Canvas(
        modifier = modifier.fillMaxWidth()
    ) {
        val path = Path()
        val dashPathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

        path.moveTo(0f, 0f)
        path.lineTo(size.width, 0f)

        drawPath(
            path = path,
            color = color,
            style = Stroke(
                1.dp.toPx(),
                pathEffect = dashPathEffect
            )
        )
    }
}

@Composable
fun RoundedCornerCanvas(color: Color = CustomTheme.colors.primarySurfaceWhite, size: Dp = 12.dp) {
    Canvas(
        modifier = Modifier
    ) {
        val path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(size.toPx(), 0f)
        path.addRoundRect(
            RoundRect(
                left = 0f,
                top = -size.toPx(),
                right = size.toPx(),
                bottom = 0f,
            )
        )
        path.lineTo(0f, -size.toPx())
        path.lineTo(0f, 0f)

        val path2 = Path()
        path2.moveTo(0f, 0f)
        path2.addOval(Rect(center = Offset(size.toPx(), -size.toPx()), radius = size.toPx()))

        val path3: Path = Path.combine(PathOperation.Difference, path, path2)

        drawPath(
            path = path3,
            color = color,
            style = Fill
        )
    }
}

@Composable
fun AppDotDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(3.dp)
            .clip(CircleShape)
            .background(CustomTheme.colors.primaryText)
    )
}


enum class DeliveryTimeDuration(val title: String, val iconRes: Int) {
    Twenty("20 mins", R.drawable.type_timer_20_mins),
    Thirty("30 mins", R.drawable.timer_30mins),
    FortyFive("45 mins", R.drawable.timer_45mins),
    FiftyFive("55 mins", R.drawable.timer_55mins),
}