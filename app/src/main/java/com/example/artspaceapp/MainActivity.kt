package com.example.artspaceapp




import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape


import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.model.ArtImage
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtImageApp()
                }
            }
        }
    }
}

@Composable
fun ArtImageApp() {
    var currentIndex by remember { mutableStateOf(0) }
    val artImageList = DataSource().loadArtImage()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtImageCard(
            artImage = artImageList[currentIndex],
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .size(200.dp),
        )
        Spacer(modifier = Modifier.height(40.dp))

        ArtworkDescription(artImageList[currentIndex])

        Spacer(modifier = Modifier.height(16.dp))

        NavigationButtons(
            onPreviousClick = {
                currentIndex = (currentIndex - 1).coerceIn(0, artImageList.size - 1)
            },
            onNextClick = { currentIndex = (currentIndex + 1).coerceIn(0, artImageList.size - 1) }
        )
    }
}

//@Composable
//fun ArtImageList(artImageList: List<ArtImage>, modifier: Modifier=Modifier){
//    LazyColumn(modifier=modifier){
//        items(artImageList){artImage ->
//            ArtImageCard(
//                artImage = artImage,
//                modifier = Modifier.padding(50.dp)
//            )
//        }
//    }
//}


@Composable
fun ArtImageCard(artImage: ArtImage, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White),
        border = BorderStroke(1.dp, Color.LightGray),
        shape = RectangleShape,
        modifier = modifier
            .size(100.dp)
            .padding(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    )
    {
            Image(
                painter = painterResource(artImage.imageResourceId),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                contentScale = ContentScale.Crop,

            )
    }
}

@Composable
fun ArtworkDescription(artImage: ArtImage) {

    Box( modifier = Modifier
        .background(color = Color.LightGray)
        .padding(15.dp)
        .size(270.dp, 70.dp),
    ){
        Column(	modifier = Modifier
        .fillMaxWidth()
        ,horizontalAlignment = Alignment.Start,

        ){
        Row{
            Text(
                text = stringResource(id = artImage.titleResourceId),
                fontWeight = FontWeight.Light,
                color = colorResource(id = R.color.black),
                fontSize = 32.sp

            )
        }
        Row{
            Text(
                text = stringResource(id = artImage.authorResourceId),
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                fontSize = 16.sp
            )
        }
    }

    }
}

@Composable
fun NavigationButtons(onPreviousClick: () -> Unit, onNextClick: () -> Unit) {
    Column (modifier = Modifier
               .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(onClick = onPreviousClick,
                modifier = Modifier.height(40.dp).width(150.dp) ) {
                Text("Previous")
            }

            Button(onClick = onNextClick,
                modifier = Modifier.height(40.dp).width(150.dp) ) {
                Text("Next")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtImagePreview() {
    ArtSpaceAppTheme {
        val artImage1 = ArtImage(R.string.art_title3,R.string.art_author,R.drawable.img_9077)
        ArtImageCard(artImage = artImage1)
    }
}