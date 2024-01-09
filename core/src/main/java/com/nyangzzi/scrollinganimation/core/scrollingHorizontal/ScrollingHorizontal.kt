package com.nyangzzi.scrollinganimation.core.scrollingHorizontal

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 *
 */
@Composable
fun ScrollingHorizontal(
    modifier : Modifier = Modifier,
    shape: Shape = RectangleShape,
    deceleration: Float = 15f,
    direction: HorizontalDirection = HorizontalDirection.LeftToRight,
    content: @Composable BoxScope.() -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "ScrollingHorizontal")

    Box(modifier = Modifier
        .clip(shape).then(modifier)) {

        var contentWidth by remember {
            mutableStateOf(0.dp)
        }

        val density = LocalDensity.current

        BoxWithConstraints(modifier = Modifier
            .onSizeChanged {
                contentWidth = with(density){ it.width.toDp() }
            }) {

            val speed = (contentWidth + this.maxWidth).value * deceleration

            val positionState by infiniteTransition.animateValue(
                initialValue = when(direction) {
                    HorizontalDirection.LeftToRight -> -contentWidth
                    HorizontalDirection.RightToLeft -> this.maxWidth
                },
                targetValue = when(direction) {
                    HorizontalDirection.LeftToRight -> this.maxWidth
                    HorizontalDirection.RightToLeft -> -contentWidth
                },
                typeConverter = Dp.VectorConverter,
                animationSpec = infiniteRepeatable(
                    animation = tween(speed.toInt(), easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                ), label = "ScrollingHorizontal"
            )

            Box(modifier = Modifier.offset(x = positionState)) {
                content()
            }

        }
    }
}