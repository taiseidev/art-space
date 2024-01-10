package com.example.artspace

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

// Artオブジェクトのリストを作成
val artList = listOf(
    Art(R.drawable.art_1, "赤、青、白の花", "Europeana", "2020"),
    Art(R.drawable.art_2, "黒と黄色の抽象画", "Victor Grabarczyk", "2019"),
    Art(R.drawable.art_3, "紫と白の抽象画のクローズアップ", "Maria Orlova ", "2019"),
    Art(R.drawable.art_4, "建物の天井に描かれた絵", "adrianna geo", "2019"),
    Art(R.drawable.art_5, "色とりどりの抽象画", "Joel Filipe", "2017")
)

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {

    var artIndex by remember { mutableStateOf(0) }
    val currentArt = artList[artIndex]
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = modifier.height(80.dp))
        ImageSection(image = currentArt.imageUrl, modifier = modifier)
        Spacer(modifier = modifier.height(60.dp))
        ArtTitleSection(art = currentArt)
        Spacer(modifier = modifier.height(10.dp))
        ButtonSection(
            onPreviousClick = {
                if (artIndex == 0) {
                    artIndex = 4
                } else {
                    artIndex -= 1
                }
            },
            onNextClick = {
                if (artIndex == 4) {
                    artIndex = 0
                } else {
                    artIndex += 1
                }
            },
        )
    }
}

@Composable
fun ImageSection(image: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
            .shadow(elevation = 4.dp)
            .background(Color.White), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = modifier.padding(vertical = 32.dp)
        )
    }
}

@Composable
fun ArtTitleSection(art: Art, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color(0xffedeaf4)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier.padding(23.dp)
        ) {
            Text(text = art.title, fontSize = 16.sp, color = Color(0xff86878d))
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xff151419))) {
                    append(art.creator)
                }
                append("（${art.year}）")
            }, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xff86878d))
        }
    }
}

@Composable
fun ButtonSection(
    onPreviousClick: () -> Unit, onNextClick: () -> Unit, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        CustomButton(text = "Previous", onClick = onPreviousClick)
        Spacer(modifier = modifier.weight(1f))
        CustomButton(text = "Next", onClick = onNextClick)
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

data class Art(
    var imageUrl: Int,
    var title: String,
    var creator: String,
    var year: String,
)


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}