package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
//                Scaffold(
//                    topBar = {
//                        CenterAlignedTopAppBar(
//                            title = {
//                                Text(
//                                    text = "Lemonade",
//                                    fontWeight = FontWeight.Bold
//                                )
//                            },
//                            colors = TopAppBarDefaults.largeTopAppBarColors(
//                                containerColor = MaterialTheme.colorScheme.primaryContainer
//                            )
//                        )
//                    }
//                ) { innerPadding ->
//
//                    LemonApp(modifier = Modifier.padding(innerPadding))
//                }
                Scaffold() { innerPadding ->
                    LemonApp(modifier = Modifier.padding(innerPadding))
                }

            }
        }
    }
}

@Composable
fun LemonApp(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(1) }
    var quantityOfSqueeze by remember { mutableStateOf(1) }

    val imageResource = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val descriptionImg = when (step) {
        1 -> R.string.img_1_description
        2 -> R.string.img_2_description
        3 -> R.string.img_3_description
        else -> R.string.img_4_description
    }

    val labelStep = when (step) {
        1 -> R.string.first_step
        2 -> R.string.second_step
        3 -> R.string.fourth_step
        else -> R.string.fifth_step
    }

    Surface(
        modifier = modifier,
//            .fillMaxSize()
//            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(Color(0xFFECE29C))
            ) {
                Text(
                    text = "Lemonade",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Button(
                    modifier = Modifier
                        .padding(10.dp),
                    shape = RoundedCornerShape(45.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD3E8DA)
                    ),
                    onClick = {
//                        if(step == 1){
//                            quantityOfSqueeze = (2..4).random()
//                            step = 2
//                        }else if(step == 2) {
//                            quantityOfSqueeze--
//                            if(quantityOfSqueeze == 0) {
//                                step = 3
//                            }
//                        }else if(step == 3) {
//                            step = 4
//                        } else {
//                            step = 1
//                        }
                        handleStepChange(step, quantityOfSqueeze) { newStep, newQuantity ->
                            step = newStep
                            quantityOfSqueeze = newQuantity
                        }
                    }
                ) {
                    Image(
                        painter = painterResource(imageResource),
                        contentDescription = descriptionImg.toString(),
                        modifier = Modifier
                            .padding(1.dp)
                            .clip(RoundedCornerShape(4.dp))
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(labelStep),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }
        }
    }
}

fun handleStepChange(currentStep: Int, currentQuantity: Int,onStepChanged: (Int, Int)-> Unit) {
    when(currentStep) {
        1 -> onStepChanged(2, (2..4).random())
        2 -> {
            if(currentQuantity > 1) {
                onStepChanged(2, currentQuantity -1)
            } else {
                onStepChanged(3, currentQuantity)
            }
        }
        3 -> onStepChanged(4, currentQuantity)
        else -> onStepChanged(1,1)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}