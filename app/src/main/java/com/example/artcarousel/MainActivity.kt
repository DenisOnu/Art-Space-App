package com.example.artcarousel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artcarousel.ui.theme.ArtCarouselTheme
import androidx.compose.material.Card

import androidx.compose.material3.*



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtCarouselTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtCarousel()
                }
            }
        }
    }
}

data class Artwork(
    val imageResourceId: Int,
    val title: String,
    val artist: String,
    val year: String
)

// Replace with actual drawable resource IDs and artwork details
val artworks = listOf(
    Artwork(R.drawable.art1, "Multicolor Abstract Painting", "Batu Gezer", "2018"),
    Artwork(R.drawable.art2, "Portrait Of Miss Hannay With Her Dog", "John Russell", "1793"),
    Artwork(R.drawable.art3, "Photo of Multicolor Glass Wall", "Mitchell Luo", "2019")
)

@Composable
fun ArtCarousel() {
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 4.dp
        ) {
            Image(
                painter = painterResource(id = artworks[currentIndex].imageResourceId),
                contentDescription = artworks[currentIndex].title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(top=16.dp, bottom = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = artworks[currentIndex].title, style = MaterialTheme.typography.headlineMedium)
        Text(text = "${artworks[currentIndex].artist} (${artworks[currentIndex].year})", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = {
                currentIndex = when (currentIndex) {
                    0 -> artworks.size - 1 // If first artwork, show the last artwork
                    else -> currentIndex - 1 // Otherwise, show the previous artwork
                }
            }) {
                Text("Previous")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                currentIndex = when (currentIndex) {
                    artworks.size - 1 -> 0 // If last artwork, show the first artwork
                    else -> currentIndex + 1 // Otherwise, show the next artwork
                }
            }) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtCarouselTheme {
        ArtCarousel()
    }
}
