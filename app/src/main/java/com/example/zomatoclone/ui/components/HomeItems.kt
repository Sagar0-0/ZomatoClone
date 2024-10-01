package com.example.zomatoclone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zomatoclone.R
import com.example.zomatoclone.ui.theme.CustomTheme
import com.example.zomatoclone.ui.theme.ZomatoCloneTheme

@Preview(backgroundColor = 0xFF000000, showBackground = true)
@Composable
private fun Preview() {
    ZomatoCloneTheme {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppAddressPickerTopBar(
                modifier = Modifier.background(Color.White),
            ) {}
            AppSearchBar(
                modifier = Modifier.fillMaxWidth()
            )
            var currentIndex by remember {
                mutableIntStateOf(0)
            }
            AppHomeBottomBar(
                modifier = Modifier.background(Color.White),
                currentIndex = currentIndex,
            ) {
                currentIndex = it
            }

            AppRecommendedToggle(
                currentIndex = currentIndex
            ) {
                currentIndex = it
            }
        }
    }
}

@Composable
fun AppAddressPickerTopBar(
    title: String = "Home",
    address: String = "block number 23_B manik niwas...",
    modifier: Modifier = Modifier,
    color: Color = CustomTheme.colors.red,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.onClick { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            modifier = Modifier.size(26.dp),
            painter = painterResource(id = R.drawable.type_location),
            contentDescription = null,
            tint = color
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = CustomTheme.typography.heading,
                    color = CustomTheme.colors.primaryText
                )
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null,
                    tint = CustomTheme.colors.primaryText
                )
            }

            Text(
                text = address,
                style = CustomTheme.typography.bodyMedium,
                color = CustomTheme.colors.secondaryText,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(
    modifier: Modifier = Modifier,
    color: Color = CustomTheme.colors.red,
    shape: Shape = CustomTheme.shape.card
) {
    val interactionSource = remember { MutableInteractionSource() }
    Shadowed(modifier, offsetY = 0.dp, blurRadius = 4.dp) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(CustomTheme.colors.primarySurfaceWhite)
                .border(
                    0.4.dp,
                    color = CustomTheme.colors.secondaryDivider,
                    shape = shape
                ),
            value = TextFieldValue(),
            onValueChange = {},
            interactionSource = interactionSource
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = "",
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                shape = shape,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        text = "Search \"biryani\"",
                        style = CustomTheme.typography.bodyLarge,
                        color = CustomTheme.colors.tertiaryText
                    )
                },
                contentPadding = PaddingValues(0.dp),
                container = {},
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.type_search),
                        contentDescription = null,
                        tint = color
                    )
                },
                trailingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(25.dp)
                                .width(1.dp)
                                .background(CustomTheme.colors.primaryDivider)
                        )
                        Width(value = 12)
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.type_microhphone),
                            contentDescription = null,
                            tint = color
                        )
                        Width(value = 10)
                    }
                }
            )
        }
    }
}

@Composable
fun AppHomeBottomBar(
    currentIndex: Int,
    modifier: Modifier = Modifier,
    color: Color = CustomTheme.colors.red,
    onClick: (Int) -> Unit
) {
    val items = listOf(
        Pair("Delivery", R.drawable.type_delivery),
        Pair("Dining", R.drawable.type_dining),
        Pair("Money", R.drawable.type_money),
    )
    Shadowed(modifier) {
        Row(
            modifier = Modifier
                .background(CustomTheme.colors.primarySurfaceWhite)
                .fillMaxWidth()
                .padding(horizontal = 17.dp)
        ) {
            items.forEachIndexed { index, item ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .onClick { onClick(index) }
                        .padding(start = 15.dp, end = 15.dp, bottom = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .clip(RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 4.dp))
                            .background(
                                if (currentIndex == index) {
                                    color
                                } else {
                                    Color.Transparent
                                }
                            )
                    )
                    Height(value = 6)
                    Icon(
                        painter = painterResource(id = item.second),
                        contentDescription = null,
                        tint = if (currentIndex == index) {
                            color
                        } else {
                            CustomTheme.colors.iconSecondary
                        }
                    )
                    Height(value = 6)
                    Text(
                        text = item.first,
                        style = CustomTheme.typography.bodySemibold,
                        color = if (currentIndex == index) {
                            CustomTheme.colors.textBlack
                        } else {
                            CustomTheme.colors.iconSecondary
                        }
                    )
                }
            }

        }
    }
}

@Composable
fun AppRecommendedToggle(
    currentIndex: Int,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    val items = listOf(
        Pair("Recommended", null),
        Pair("Favourites", R.drawable.heart),
    )
    Row(
        modifier = modifier
            .clip(CustomTheme.shape.labelsRadiusLarge)
            .background(CustomTheme.colors.primarySurfaceWhite),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .background(
                        if (currentIndex == index) {
                            CustomTheme.colors.redSubdued
                        } else {
                            Color.Transparent
                        }
                    )
                    .border(
                        width = 1.dp,
                        color = if (currentIndex == index) {
                            CustomTheme.colors.red
                        } else {
                            CustomTheme.colors.secondaryDivider
                        },
                        shape = if (index == 0) {
                            CustomTheme.shape.labelsRadiusLarge.copy(
                                topEnd = CornerSize(0.dp),
                                bottomEnd = CornerSize(0.dp)
                            )
                        } else if (index == items.size - 1) {
                            CustomTheme.shape.labelsRadiusLarge.copy(
                                topStart = CornerSize(0.dp),
                                bottomStart = CornerSize(0.dp)
                            )
                        } else {
                            RectangleShape
                        }
                    )
                    .onClick { onClick(index) }
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item.second?.let {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(id = it),
                        contentDescription = null,
                        tint = CustomTheme.colors.red
                    )
                }
                Text(
                    text = item.first,
                    style = CustomTheme.typography.body,
                    color = if (currentIndex == index) {
                        CustomTheme.colors.secondaryText
                    } else {
                        CustomTheme.colors.tertiaryText
                    }
                )
            }
        }
    }
}