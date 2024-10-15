package com.sagar.designsystem.molecules

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            AppButtonFilled(modifier = Modifier.fillMaxWidth(), text = "Add item $55") {}
            AppButtonFilled("View cart", color = CustomTheme.colors.red) {}
            var count by remember {
                mutableIntStateOf(1)
            }

            AppAddButtonGreenLarge(
                count = count,
                onAddClick = {
                    count++
                },
                onSubtractClick = {
                    count--
                }
            )
        }
    }
}

@Composable
private fun AppButtonText(
    text: String, modifier: Modifier = Modifier, color: Color = CustomTheme.colors.textWhite
) {
    Text(
        modifier = modifier,
        text = text,
        style = CustomTheme.typography.bodySemiboldLarger,
        color = color,
        textAlign = TextAlign.Center
    )
}

@Composable
fun AppButtonFilled(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = CustomTheme.colors.green,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CustomTheme.shape.labelsRadiusLarge)
            .background(color)
            .onClick { onClick() }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        AppButtonText(text)
    }
}

@Composable
fun AppAddButtonGreenLarge(
    count: Int,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
    onSubtractClick: () -> Unit
) {
    Crossfade(
        targetState = count == 0, label = ""
    ) {
        if (it) {
            Box(
                modifier = modifier
                    .clip(CustomTheme.shape.labelsRadiusLarge)
                    .background(CustomTheme.colors.greenSubdued)
                    .border(0.6.dp, CustomTheme.colors.green, CustomTheme.shape.labelsRadiusLarge)
                    .height(40.dp)
                    .width(122.dp)
                    .onClick { onAddClick() }
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                AppButtonText(
                    text = "ADD",
                    color = CustomTheme.colors.green
                )
                Icon(
                    modifier = Modifier
                        .size(10.5.dp)
                        .align(Alignment.TopEnd),
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = null,
                    tint = CustomTheme.colors.green
                )
            }
        } else {
            Box(
                modifier = modifier
                    .clip(CustomTheme.shape.labelsRadiusLarge)
                    .background(CustomTheme.colors.green)
                    .height(40.dp)
                    .width(122.dp)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(10.dp)
                            .onClick { onSubtractClick() },
                        painter = painterResource(id = R.drawable.type_subtract),
                        contentDescription = null,
                        tint = CustomTheme.colors.textWhite
                    )
                    AppButtonText(
                        modifier = Modifier.weight(1f),
                        text = count.toString(), color = CustomTheme.colors.textWhite
                    )
                    Icon(
                        modifier = Modifier
                            .size(10.dp)
                            .onClick { onAddClick() },
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = null,
                        tint = CustomTheme.colors.textWhite
                    )
                }

            }
        }
    }

}