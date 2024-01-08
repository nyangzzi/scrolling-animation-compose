package com.nyangzzi.scrollingtextanimation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.nyangzzi.scrollingtextanimation.ui.theme.ScrollingTextAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollingTextAnimationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

            ScrollingTextAnimation(text = "애니메이션 테스트입니다")
            ScrollingTextAnimation(text = "애니메이션 테스트입니다")
            ScrollingTextAnimation(text = "애니메이션 테스트입니다")

    }
}

@Composable
fun ScrollingTextAnimation(text: String = ""){

    val infiniteTransition = rememberInfiniteTransition(label = "")

    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RectangleShape)) {

        var textWidth by remember {
            mutableStateOf(0.dp)
        }

        val density = LocalDensity.current

        BoxWithConstraints(modifier = Modifier
            .onSizeChanged {
                textWidth = with(density){ it.width.toDp() }

            }) {

            val speed = (textWidth + this.maxWidth).value.toInt()

            val positionState by infiniteTransition.animateValue(
                initialValue = this.maxWidth,
                targetValue = -textWidth,
                typeConverter = Dp.VectorConverter,
                animationSpec = infiniteRepeatable(
                    animation = tween(speed * 15, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                ), label = ""
            )

            Text(text = text, modifier = Modifier.offset(x = positionState))


        }
    }
}