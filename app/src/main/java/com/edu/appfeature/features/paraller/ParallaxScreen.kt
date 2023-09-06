package com.edu.appfeature.features.paraller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.edu.appfeature.R
import com.edu.appfeature.features.navehostwithdatapass.DataViewModel

@Composable
fun ParallaxViewScreen(navHostController: NavHostController, dataViewModel: DataViewModel) {

    val data = dataViewModel.dataDetails

    val scrollState = rememberScrollState()

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .graphicsLayer {
                        alpha = 1f - (scrollState.value.toFloat() / scrollState.maxValue)
                        translationY = 0.5f * scrollState.value
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter("${ data?.imageUri }"),
//                    painterResource(id = R.mipmap.img),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)) {
                Text(
                    text = "${ data?.name }",
                    modifier = Modifier.padding(10.dp),
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Text(
                    text = "${ data?.imageUri }",
                    modifier = Modifier.padding(10.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Blue
                    )
                )
                Text(
                    text = "Clark Joseph Kent (né Kal-El), best known by his superhero persona Superman, is a superhero in the DC Extended Universe series of films, based on the DC Comics character of the same name created by Jerry Siegel and Joe Schuster. In the films, he is a survivor from the destroyed planet Krypton who lands on Earth and develops superhuman abilities due to environmental differences between the planets and their respective star systems.",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray,
                        textAlign = TextAlign.Justify
                    )
                )
                Text(
                    text = "Clark Joseph Kent (né Kal-El), best known by his superhero persona Superman, is a superhero in the DC Extended Universe series of films, based on the DC Comics character of the same name created by Jerry Siegel and Joe Schuster. In the films, he is a survivor from the destroyed planet Krypton who lands on Earth and develops superhuman abilities due to environmental differences between the planets and their respective star systems.",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray,
                        textAlign = TextAlign.Justify
                    )
                )
            }
        }
    }
}