package com.nyangzzi.scrollinganimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nyangzzi.scrollinganimation.core.scrollingHorizontal.HorizontalDirection
import com.nyangzzi.scrollinganimation.core.scrollingHorizontal.ScrollingHorizontal
import com.nyangzzi.scrollinganimation.core.scrollingVertical.ScrollingVertical
import com.nyangzzi.scrollinganimation.core.scrollingVertical.VerticalDirection
import com.nyangzzi.scrollinganimation.ui.theme.ScrollingAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollingAnimationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Sample()
                }
            }
        }
    }
}

@Composable
fun Sample() {

    val radioOption = listOf( "horizontal", "vertical")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOption[0]) }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(top = 10.dp)) {

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            radioOption.map {
                Row(modifier = Modifier.selectable(
                    selected = (it == selectedOption),
                    onClick = { onOptionSelected(it) }),
                    verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (it == selectedOption),
                        onClick = { onOptionSelected(it) })
                    Text(text = it)
                }
            }
        }

        when(selectedOption){
            radioOption[0] -> ExampleScrollingHorizontal()
            radioOption[1] -> ExampleScrollingVertical()
        }
    }

}

@Composable
private fun ExampleScrollingHorizontal(){

    val radioOption = listOf( "left to right", "right to left")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOption[0]) }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp),) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            radioOption.map {
                Row(
                    modifier = Modifier.selectable(
                        selected = (it == selectedOption),
                        onClick = { onOptionSelected(it) }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (it == selectedOption),
                        onClick = { onOptionSelected(it) })
                    Text(text = it)
                }
            }
        }

        Divider(color = Color.LightGray)

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            listOf(0.5f, 1f, 2f, 4f, 8f, 16f, 32f).map {
                HorizontalText(
                    deceleration = it, direction =
                    when (selectedOption) {
                        radioOption[0] -> HorizontalDirection.LeftToRight
                        else -> HorizontalDirection.RightToLeft
                    }
                )
            }
        }
    }
}

@Composable
private fun HorizontalText(deceleration: Float, direction: HorizontalDirection){
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("deceleration: $deceleration")
        ScrollingHorizontal(deceleration = deceleration, direction = direction, modifier = Modifier.fillMaxWidth()) {
            Text("test animation")
        }
    }
}


@Composable
private fun ExampleScrollingVertical(){

    val radioOption = listOf( "top to bottom", "bottom to top")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOption[0]) }
    Column(verticalArrangement = Arrangement.spacedBy(16.dp),) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            radioOption.map {
                Row(
                    modifier = Modifier.selectable(
                        selected = (it == selectedOption),
                        onClick = { onOptionSelected(it) }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (it == selectedOption),
                        onClick = { onOptionSelected(it) })
                    Text(text = it)
                }
            }
        }

        Divider(color = Color.LightGray)
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .horizontalScroll(rememberScrollState()).padding(horizontal = 16.dp)
        ) {

            listOf(0.5f, 1f, 2f, 4f, 8f, 16f, 32f).map {
                VerticalText(
                    deceleration = it, direction =
                    when (selectedOption) {
                        radioOption[0] -> VerticalDirection.TopToBottom
                        else -> VerticalDirection.BottomToTop
                    }
                )
            }
        }
    }
}

@Composable
private fun VerticalText(deceleration: Float, direction: VerticalDirection){
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("deceleration: $deceleration")
        ScrollingVertical(deceleration = deceleration, direction = direction, modifier = Modifier.fillMaxHeight()) {
            Text("test animation")
        }
    }
}