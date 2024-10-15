package com.sagar.designsystem.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sagar.designsystem.CustomTheme
import com.sagar.designsystem.R
import com.sagar.designsystem.ZomatoCloneTheme
import com.sagar.designsystem.utils.onClick

@Preview(backgroundColor = 0xFF000000)
@Composable
private fun Preview() {
    ZomatoCloneTheme {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppSortChip() {

            }
            AppSortChip(selectedSortCount = 1) {

            }

            AppAssistChip(text = "Sort", isSelected = false) {

            }

            AppAssistChip(text = "Spicy", isSelected = true) {

            }

        }
    }
}

@Composable
fun AppChipText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = CustomTheme.colors.primaryText
) {
    Text(
        text = text,
        style = CustomTheme.typography.bodySemibold,
        fontSize = 11.sp,
        letterSpacing = (-0.2).sp,
        color = color,
        modifier = modifier
    )
}

@Composable
fun AppSortChip(
    modifier: Modifier = Modifier,
    selectedSortCount: Int = 0,
    onClick: () -> Unit
) {
    val isSelected = remember(selectedSortCount) {
        selectedSortCount > 0
    }
    val borderModifier = if (isSelected) {
        Modifier
            .background(CustomTheme.colors.greenSubdued)
            .border(
                0.4.dp,
                CustomTheme.colors.green,
                CustomTheme.shape.labelsRadiusLarge
            )
    } else {
        Modifier
            .background(CustomTheme.colors.primarySurfaceWhite)
            .border(
                0.8.dp,
                CustomTheme.colors.primaryDivider,
                CustomTheme.shape.labelsRadiusLarge
            )
    }
    Row(
        modifier = modifier
            .clip(CustomTheme.shape.labelsRadiusLarge)
            .then(borderModifier)
            .onClick { onClick() }
            .padding(vertical = 6.dp, horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Icon(
            modifier = Modifier.height(14.dp),
            painter = painterResource(id = R.drawable.type_sort),
            contentDescription = null,
            tint = CustomTheme.colors.primaryText
        )
        AppChipText(
            text = "Sort${
                if (selectedSortCount > 0) {
                    " ($selectedSortCount)"
                } else {
                    ""
                }
            }"
        )
        Icon(
            modifier = Modifier.height(6.dp),
            painter = painterResource(id = R.drawable.type_dropdown),
            contentDescription = null,
            tint = CustomTheme.colors.primaryText
        )
    }
}


@Composable
fun AppAssistChip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderModifier = if (isSelected) {
        Modifier
            .background(CustomTheme.colors.greenSubdued)
            .border(
                0.8.dp,
                CustomTheme.colors.green,
                CustomTheme.shape.labelsRadiusLarge
            )
    } else {
        Modifier
            .background(CustomTheme.colors.primarySurfaceWhite)
            .border(
                0.8.dp,
                CustomTheme.colors.primaryDivider,
                CustomTheme.shape.labelsRadiusLarge
            )
    }
    Row(
        modifier = modifier
            .clip(CustomTheme.shape.labelsRadiusLarge)
            .then(borderModifier)
            .onClick { onClick() }
            .padding(vertical = 6.dp, horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Icon(
            modifier = Modifier.height(15.dp),
            painter = painterResource(id = R.drawable.type_premium_offer),
            contentDescription = null,
            tint = Color.Unspecified
        )
        AppChipText(
            text = text
        )
        if (isSelected) {
            Icon(
                modifier = Modifier.height(15.dp),
                painter = painterResource(id = R.drawable.type_cross),
                contentDescription = null,
                tint = CustomTheme.colors.primaryText
            )
        }
    }
}