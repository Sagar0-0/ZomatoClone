package com.example.zomatoclone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
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
            AppLabelNew()
            AppLabelNearFast()
            AppRatingLabel(4.3)
        }
    }

}

@Composable
fun AppLabelText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = CustomTheme.colors.green,
    fontSize: TextUnit = 11.sp,
) {
    Text(
        text = text,
        style = CustomTheme.typography.label,
        fontSize = fontSize,
        letterSpacing = (-0.2).sp,
        color = color,
        modifier = modifier
    )
}

@Composable
fun AppLabelNew(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(CustomTheme.shape.labelsRadius)
            .background(CustomTheme.colors.greenSubdued)
            .padding(start = 3.dp, top = 2.dp, bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppLabelText(text = "NEW")
        Icon(
            modifier = Modifier.height(14.dp),
            painter = painterResource(id = R.drawable.type_lightning),
            contentDescription = null,
            tint = CustomTheme.colors.green
        )
    }
}

@Composable
fun AppLabelNearFast(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(CustomTheme.shape.labelsRadiusSmall)
            .background(CustomTheme.colors.greenSubdued)
            .border(
                0.5.dp,
                CustomTheme.colors.green,
                CustomTheme.shape.labelsRadiusSmall
            )
            .padding(start = 1.dp, top = 4.dp, bottom = 4.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.height(14.dp),
            painter = painterResource(id = R.drawable.type_lightning),
            contentDescription = null,
            tint = CustomTheme.colors.green
        )
        AppLabelText(text = "NEAR & FAST")
    }
}

@Composable
fun AppRatingLabel(
    rating: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(CustomTheme.shape.labelsRadiusSmall)
            .background(CustomTheme.colors.green)
            .padding(start = 3.dp, top = 2.dp, bottom = 2.dp, end = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppLabelText(text = rating.toString(), color = CustomTheme.colors.textWhite, fontSize = 14.sp)
        Icon(
            modifier = Modifier.height(12.dp),
            painter = painterResource(id = R.drawable.type_star),
            contentDescription = null,
            tint = CustomTheme.colors.primarySurfaceWhite
        )
    }
}