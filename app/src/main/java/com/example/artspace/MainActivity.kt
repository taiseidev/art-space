package com.example.artspace

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
    ) {
        ImageSection(modifier = modifier)
        Spacer(modifier = modifier.height(80.dp))
        ArtTitleSection()
        Spacer(modifier = modifier.height(40.dp))
        ButtonSection()
    }
}

@Composable
fun ImageSection(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(horizontal = 16.dp)
            .shadow(elevation = 4.dp)
            .background(Color.White), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = null,
            modifier = modifier.padding(vertical = 32.dp)
        )
    }
}

@Composable
fun ArtTitleSection(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp)
            .background(Color.LightGray), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier.padding(23.dp)
        ) {
            Text(text = "ここにアートのタイトルを挿入します。", fontSize = 16.sp)
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("作者名")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("（2021）")
                }
            }, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ButtonSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        CustomButton(text = "Previous", onClick = {})
        Spacer(modifier = modifier.weight(1f))
        CustomButton(text = "Next", onClick = {})
    }
}

@Composable
fun CustomButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    var customColor = Color(0xff495d92)
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = customColor),
        onClick = onClick,
        modifier = modifier.size(150.dp, 40.dp)
    ) {
        Text(text, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}