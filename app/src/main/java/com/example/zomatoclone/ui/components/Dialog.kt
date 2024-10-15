package com.example.zomatoclone.ui.components


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sagar.designsystem.CustomTheme
import com.sagar.designsystem.ZomatoCloneTheme
import com.sagar.designsystem.molecules.AppButtonFilled
import com.sagar.designsystem.utils.onClickNoRipple
import kotlinx.coroutines.delay


@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun Preview() {
    ZomatoCloneTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppButtonFilled("testing") { }
            var isDialogVisible by rememberSaveable { mutableStateOf(false) }
            AppDialogLayout(
                modifier = Modifier.fillMaxSize(),
                isDialogVisible = isDialogVisible,
                onCloseDialog = {
                    isDialogVisible = false
                },
                dialog = {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        repeat(5) {
                            Text(
                                modifier = Modifier.clickable { },
                                text = "Dialog Content",
                                style = CustomTheme.typography.bodySemibold,
                                color = CustomTheme.colors.textBlack
                            )
                        }
                    }
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AnimateIncrementDecrementSample()
                    Button(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(20.dp),
                        onClick = { isDialogVisible = !isDialogVisible }) {
                        Text(text = "Open/Close Dialog")
                    }
                }
            }
        }
    }
}

@Composable
fun AppDialogLayout(
    modifier: Modifier = Modifier,
    isDialogVisible: Boolean = false,
    onCloseDialog: () -> Unit = {},
    animationDurationMillis: Int = 200,
    dialog: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    val bgColor by animateColorAsState(
        targetValue = if (isDialogVisible) {
            CustomTheme.colors.primarySurfaceBlack.copy(0.5f)
        } else {
            Color.Transparent
        }, label = "",
        animationSpec = tween(
            durationMillis = animationDurationMillis / 2,
            easing = FastOutLinearInEasing
        )
    )
    Box(modifier = modifier.animateContentSize()) {
        content()

        val clickableModifier = if (isDialogVisible) {
            Modifier.onClickNoRipple { onCloseDialog() }
        } else {
            Modifier
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
                .then(clickableModifier)
        )

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp, end = 80.dp)
                .onClickNoRipple { },
            visible = isDialogVisible,
            enter = slideInVertically(
                animationSpec = tween(
                    durationMillis = animationDurationMillis,
                    easing = FastOutLinearInEasing
                ),
                initialOffsetY = {
                    it/2
                }
            ),
            exit = slideOutVertically(
                animationSpec = tween(
                    durationMillis = animationDurationMillis,
                    easing = FastOutLinearInEasing
                ),
                targetOffsetY = {
                    it/2
                }
            )
        ) {
            AppDialogContentCard {
                var isContentVisible by rememberSaveable {
                    mutableStateOf(false)
                }

                LaunchedEffect(key1 = Unit) {
                    delay(600)
                    isContentVisible = true
                }

                if (!isContentVisible) {
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                    )
                } else {
                    dialog()
                }
            }
        }
    }
}

@Composable
fun AppDialogContentCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .clip(CustomTheme.shape.cardLarge)
            .background(CustomTheme.colors.primarySurfaceWhite)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 600,
                    delayMillis = 600,
                    easing = LinearEasing
                )
            )
    ) {
        content()
    }
}

@Composable
fun AnimateIncrementDecrementSample() {
    Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        var count by remember { mutableStateOf(0) }
        // The `AnimatedContent` below uses an integer count as its target state. So when the
        // count changes, it will animate out the content associated with the previous count, and
        // animate in the content associated with the target state.
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // We can define how the new target content comes in and how initial content
                // leaves in the ContentTransform. Here we want to create the impression that the
                // different numbers have a spatial relationship - larger numbers are
                // positioned (vertically) below smaller numbers.
                if (targetState > initialState) {
                    // If the incoming number is larger, new number slides up and fades in while
                    // the previous (smaller) number slides up to make room and fades out.
                    slideInVertically { it } + fadeIn() togetherWith
                            slideOutVertically { -it } + fadeOut()
                } else {
                    // If the incoming number is smaller, new number slides down and fades in
                    // while
                    // the previous number slides down and fades out.
                    slideInVertically { -it } + fadeIn() togetherWith
                            slideOutVertically { it } + fadeOut()
                    // Disable clipping since the faded slide-out is desired out of bounds, but
                    // the size transform is still needed from number getting longer
                }
                    .using(SizeTransform(clip = false)) // Using default spring for the size change.
            }, label = ""
        ) { targetCount ->
            // This establishes a mapping between the target state and the content in the form of a
            // Composable function. IMPORTANT: The parameter of this content lambda should
            // *always* be used. During the content transform, the old content will be looked up
            // using this lambda with the old state, until it's fully animated out.

            // Since AnimatedContent differentiates the contents using their target states as the
            // key, the same composable function returned by the content lambda like below will be
            // invoked under different keys and therefore treated as different entities.
            Text("$targetCount", fontSize = 20.sp)
        }
        Spacer(Modifier.size(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = { count-- }) { Text("Minus") }
            Spacer(Modifier.size(60.dp))
            Button(onClick = { count++ }) { Text("Plus ") }
        }
    }
}

















