package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.ComposeAnimationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimationTheme {
                AnimatedContentDemo()
            }
        }
    }


    @Composable
    fun AnimatedContentDemo() {
        var startAnimation by remember { mutableStateOf(false) }
        val offsetY by animateDpAsState(
            targetValue = if (startAnimation) 700.dp else 0.dp,
            animationSpec = tween(durationMillis = 2000)
        )
        val animatedBackgroundColor by animateColorAsState(
            targetValue = if (startAnimation) Color(0xEEEEEE) else Color(0xFF4CAF50),
            animationSpec = tween(durationMillis = 5000)
        )
        val animatedOffsetY by animateDpAsState(
            targetValue = if (startAnimation) 0.dp else 300.dp,
            animationSpec = tween(durationMillis = 2000)
        )
        val animatedAlpha by animateFloatAsState(
            targetValue = if (startAnimation) 0f else 1f,
            animationSpec = tween(durationMillis = 1500, delayMillis = 1500) // Delay to start fade-out after position animation
        )
        // Animate opacity for the second text
        val newTextAlpha by animateFloatAsState(
            targetValue = if (startAnimation) 1f else 0f,
            animationSpec = tween(durationMillis = 1500, delayMillis = 1500) // Delay to start fade-in after first text finishes
        )
//        val newTextAlpha by animateFloatAsState(
//            targetValue = if (startAnimation) 0f else 1f,
//            animationSpec = tween(durationMillis = 1500, delayMillis = 1500) // Delay to start fade-in after first text finishes
//        )
        LaunchedEffect(Unit) {
            startAnimation = true
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Column(


             //    verticalArrangement =Arrangement.Center,
             horizontalAlignment = Alignment.CenterHorizontally,

                modifier = Modifier
                    .offset(y = offsetY)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(animatedBackgroundColor)
                    .padding(0.dp),
              //  horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                Text(
                 textAlign = TextAlign.Center,
                    text = "Scan Completed",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .graphicsLayer(alpha = animatedAlpha)
                        .offset(y = animatedOffsetY) // Animate the vertical position
                       // .padding(16.dp) // Optional padding

                )


                Text(
                    text = "New Text at Bottom",
                    color = Color.Black,

                    modifier = Modifier
                        .align(Alignment.Start)
                        .graphicsLayer(alpha = newTextAlpha)
                        .padding(20.dp)
                )
            }
        }
    }

    @Composable
    fun AnimatedContentDemoq() {
        var startAnimation by remember { mutableStateOf(false) }

        val animatedOffsetY by animateDpAsState(
            targetValue = if (startAnimation) 500.dp else 0.dp,
            animationSpec = tween(durationMillis = 1000)
        )

        val animatedBackgroundColor by animateColorAsState(
            targetValue = if (startAnimation) Color.Blue else Color.Red,
            animationSpec = tween(durationMillis = 1000)
        )

        LaunchedEffect(Unit) {
            startAnimation = true
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(animatedBackgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset(y = animatedOffsetY)
                    .background(Color.White)
                    .align(Alignment.Center)
            )
        }
    }
}