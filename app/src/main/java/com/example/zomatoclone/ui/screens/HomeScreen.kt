package com.example.zomatoclone.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zomatoclone.R
import com.sagar.designsystem.CustomTheme
import com.sagar.designsystem.ZomatoCloneTheme
import com.sagar.designsystem.molecules.AppAddressPickerTopBar
import com.sagar.designsystem.molecules.AppAssistChip
import com.sagar.designsystem.molecules.AppHomeBottomBar
import com.sagar.designsystem.molecules.AppRecommendedToggle
import com.sagar.designsystem.molecules.AppSearchBar
import com.sagar.designsystem.molecules.AppSortChip
import com.sagar.designsystem.molecules.AppVegModeToggle
import com.sagar.designsystem.templates.AppRestaurantCardItemLarge
import com.sagar.designsystem.templates.AppRestaurantCardItemSmall
import com.sagar.designsystem.utils.Height
import com.sagar.designsystem.utils.Width

@Preview
@Composable
private fun Preview() {
    ZomatoCloneTheme {
        HomeScreen()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    var currentTab by remember {
        mutableIntStateOf(0)
    }
    var isVegMode by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primarySurfaceWhite)
    ) {
        if (currentTab == 0) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(top = 10.dp),
            ) {
                stickyHeader {
                    AddressTopBar()
                }
                stickyHeader {
                    SearchTopBar(isVegMode) {
                        isVegMode = !isVegMode
                    }
                }

                item {
                    OfferImage()
                }

                item {
                    ForYouSection()
                }

                item {
                    RestaurantsSection()
                }

                items(5) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    ) {
                        AppRestaurantCardItemLarge()
                        Height(value = 30)
                    }
                }
            }
        }
        AppHomeBottomBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            currentIndex = currentTab
        ) {
            currentTab = it
        }
    }
}

@Composable
fun RestaurantsSection() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HomeScreenDivider("Restaurants")
        Height(value = 16)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                AppSortChip() {

                }
            }
            items(5) {
                AppAssistChip(text = "Great Offer", isSelected = false) {

                }
            }
        }
        Height(value = 24)
    }
}

@Composable
fun ForYouSection() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HomeScreenDivider("For You")
        Height(value = 16)
        AppRecommendedToggle(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 12.dp),
            currentIndex = 0
        ) {

        }
        Height(value = 26)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(5) {
                AppRestaurantCardItemSmall()
            }
        }
        Height(value = 30)


        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(5) {
                AppRestaurantCardItemSmall()
            }
        }
        Height(value = 30)
    }
}

@Composable
fun HomeScreenDivider(text: String) {
    Box {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 12.dp)
                .background(CustomTheme.colors.tertiaryDivider)
                .align(Alignment.Center)
        )
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .background(CustomTheme.colors.primarySurfaceWhite)
                .padding(10.dp),
            text = text,
            style = CustomTheme.typography.titleMedium,
            color = CustomTheme.colors.primaryText
        )
    }
}

@Composable
fun OfferImage() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CustomTheme.shape.cardLarge)
            .padding(bottom = 20.dp),
        painter = painterResource(id = R.drawable.offer_placeholder), contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun SearchTopBar(isVegMode: Boolean, onVegModeClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CustomTheme.colors.primarySurfaceWhite)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppSearchBar(modifier = Modifier.weight(1f))
        Width(value = 16)
        AppVegModeToggle(isVegMode) {
            onVegModeClick()
        }
    }
}

@Composable
fun AddressTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppAddressPickerTopBar(modifier = Modifier.weight(1f))
        Width(value = 13)
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(CustomTheme.colors.tertiaryText),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "S",
                style = CustomTheme.typography.body,
                color = CustomTheme.colors.primaryText
            )
        }
    }
}
