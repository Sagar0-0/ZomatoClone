package com.example.zomatoclone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zomatoclone.R
import com.example.zomatoclone.ui.theme.CustomTheme
import com.example.zomatoclone.ui.theme.ZomatoCloneTheme


@Preview(backgroundColor = 0xFF000000)
@Composable
private fun Preview() {
    ZomatoCloneTheme {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var isEnabled by remember {
                mutableStateOf(false)
            }
            AppVegModeToggle(isEnabled = isEnabled) {
                isEnabled = !isEnabled
            }
            AppVegModeToggle(isEnabled = !isEnabled) {
                isEnabled = !isEnabled
            }

            AppCheckboxSquare(
                isSelected = isEnabled,
                color = CustomTheme.colors.red
            ) {
                isEnabled = !isEnabled
            }

            AppCheckboxSquare(
                isSelected = !isEnabled,
                color = CustomTheme.colors.green
            ) {
                isEnabled = !isEnabled
            }

            AppCheckboxCircle(
                isSelected = isEnabled,
                color = CustomTheme.colors.red
            ) {
                isEnabled = !isEnabled
            }

            AppCheckboxCircle(
                isSelected = !isEnabled,
                color = CustomTheme.colors.green
            ) {
                isEnabled = !isEnabled
            }
        }
    }
}

@Composable
fun AppTextVeg(modifier: Modifier = Modifier) {
    Text(
        text = "VEG",
        style = CustomTheme.typography.label,
        color = CustomTheme.colors.textBlack,
        modifier = modifier
    )
}

@Composable
fun AppTextVegMode(modifier: Modifier = Modifier) {
    Text(
        text = "MODE",
        style = CustomTheme.typography.label.copy(fontSize = 10.sp),
        color = CustomTheme.colors.textBlack,
        modifier = modifier
    )
}

@Composable
fun AppVegModeToggle(
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .width(34.dp)
    ) {
        AppTextVeg()
        AppTextVegMode()
        Height(value = 4)

        Box {
            Spacer(
                modifier = Modifier
                    .height(14.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(7.dp))
                    .background(
                        if (isEnabled) {
                            CustomTheme.colors.green
                        } else {
                            Color(0xFFD6D7DB)
                        }
                    )
                    .align(Alignment.Center)
            )
            Shadowed(
                modifier = Modifier.align(
                    if (isEnabled) {
                        Alignment.CenterStart
                    } else {
                        Alignment.CenterEnd
                    }
                ),
            ) {
                Spacer(
                    modifier = Modifier
                        .size(21.dp)
                        .clip(CircleShape)
                        .background(CustomTheme.colors.primarySurfaceWhite)
                        .onClick { onClick() }
                )
            }
        }
    }
}

@Composable
fun AppCheckboxSquare(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    color: Color = Color.Green,
    onClick: () -> Unit
) {
    Box(modifier = modifier.clip(RoundedCornerShape(3.dp)).onClick { onClick() }) {
        Icon(
            painter = painterResource(
                id = if (isSelected) {
                    R.drawable.type_checkbox_unchecked
                } else {
                    R.drawable.type_checkbox_checked
                }
            ),
            contentDescription = null, tint = color
        )
    }
}

@Composable
fun AppCheckboxCircle(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    color: Color = Color.Green,
    onClick: () -> Unit
) {
    Box(modifier = modifier.clip(CircleShape).onClick { onClick() }) {
        Icon(
            painter = painterResource(
                id = if (isSelected) {
                    R.drawable.type_radio_button_unselected
                } else {
                    R.drawable.type_radio_button_selected
                }
            ),
            contentDescription = null, tint = color
        )
    }
}

