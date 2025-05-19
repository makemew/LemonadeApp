package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Lemonade",
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Yellow,
                )
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            ActionChoices()
        }
    }
}

@Composable
fun ActionChoices() {
    var currentStep by remember { mutableIntStateOf(1) }
    var threshold by remember { mutableIntStateOf(0) }

    when(currentStep) {
        1 -> {
            LemonTextAndImage(
                painter = R.drawable.lemon_tree,
                contentDescription = R.string.lemon_tree_content_description,
                text = R.string.lemon_tree
            ) {
                currentStep = 2
                threshold = (2..4).random()
            }

        }
        2 -> {
            LemonTextAndImage(
                painter = R.drawable.lemon_squeeze,
                contentDescription = R.string.lemon_content_description,
                text = R.string.lemon
            ) {
                threshold--
                if (threshold==0) currentStep = 3
            }
        }
        3 -> {
            LemonTextAndImage(
                painter = R.drawable.lemon_drink,
                contentDescription = R.string.lemonade_content_description,
                text = R.string.lemonade
            ) {
                currentStep = 4
            }
        }
        4 -> {
            LemonTextAndImage(
                painter = R.drawable.lemon_restart,
                contentDescription = R.string.lemonade_empty_content_description,
                text = R.string.lemonade_empty
            ) {
                currentStep = 1
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LemonadeActions() {
    LemonadeApp()
}
@Composable
fun LemonTextAndImage(painter: Int, contentDescription: Int, text: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            shape = RoundedCornerShape(40.dp),
            colors = ButtonColors(
                containerColor = Color(0xFFC2EBD1),
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.primary
            ),
            onClick = onClick
        ) {
            Image(
                painter = painterResource(painter),
                contentDescription = stringResource(contentDescription),
                modifier = Modifier
                    .height(160.dp)
                    .width(128.dp),
            )
        }
        Spacer(Modifier.height(32.dp))
        Text(
            stringResource(text),
            style = TextStyle(
                fontSize = 18.sp,
            )
        )
    }
}

