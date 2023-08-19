package com.edu.appfeature.features.video

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edu.appfeature.features.api.displaypage.GooglePlaySearchButton
import com.edu.appfeature.features.api.displaypage.YouTubeSearchButton
import com.edu.appfeature.ui.theme.BabyBlue
import com.edu.appfeature.ui.theme.veryLightColor
import com.edu.appfeature.ui.util.TextView
import com.edu.appfeature.ui.util.getVideoId

@SuppressLint("MutableCollectionMutableState", "ServiceCast")
@Composable
fun VideoViewScreen() {

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val applicationName = "tik tok"
    val videoUrl = "https://www.youtube.com/watch?v=yxvaURdWJS8" // https://www.youtube.com/watch?v=yxvaURdWJS8

    val id = getVideoId(videoUrl)
    var videoId = applicationName
    if (id.isNotBlank()) {
        videoId = id
    }

    val playStoreSearchUrl = "https://play.google.com/store/search?q=$applicationName"
    val youtubeSearchUrl = "https://www.youtube.com/search?q=$videoId"
    val googleSearchUrl = "https://google.com/search?q=$applicationName"

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(veryLightColor)
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .graphicsLayer {
                        alpha = 1f - (scrollState.value.toFloat() / scrollState.maxValue)
                        translationY = 0.5f * scrollState.value
                    },
                contentAlignment = Alignment.Center
            ) {
                Column {
                    YouTubePlayerWithUrl(
                        imageUrl = "",
                        youtubeVideoUrl = videoUrl, // https://www.youtube.com/watch?v=yxvaURdWJS8
                        lifecycleOwner = LocalLifecycleOwner.current
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // play store button
                        GooglePlaySearchButton(playStoreSearchUrl = playStoreSearchUrl, context)
                        // you tube button
                        YouTubeSearchButton(videoId = videoId, context)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                // title
                TextView(
                    text = "Impossible goals save || Best save goal keeper",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 30.dp)
                )
                // google link
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.CopyAll,
                        contentDescription = null,
                        tint = BabyBlue,
                        modifier = Modifier.clickable {
                            // copy the googleSearchUrl
                            val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clipData = ClipData.newPlainText("Google Search URL", googleSearchUrl)
                            clipboardManager.setPrimaryClip(clipData)
                        }
                    )
                    TextView(
                        text = googleSearchUrl,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = BabyBlue,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif
                        ),
                        modifier = Modifier.padding(start = 5.dp)
                            .clickable {
                                val webIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(googleSearchUrl)
                                )
                                context.startActivity(webIntent)
                            }
                    )
                }

                // play store link
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.CopyAll,
                        contentDescription = null,
                        tint = BabyBlue,
                        modifier = Modifier.clickable {
                            // copy the playStoreSearchUrl
                            val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clipData = ClipData.newPlainText("Google Play Store URL", playStoreSearchUrl)
                            clipboardManager.setPrimaryClip(clipData)
                        }
                    )
                    TextView(
                        text = playStoreSearchUrl,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = BabyBlue,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif
                        ),
                        modifier = Modifier.padding(start = 5.dp)
                            .clickable {
                                val webIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(playStoreSearchUrl)
                                )
                                context.startActivity(webIntent)
                            }
                    )
                }

                // youtube link
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.CopyAll,
                        contentDescription = null,
                        tint = BabyBlue,
                        modifier = Modifier.clickable {
                            // copy the youtubeSearchUrl
                            val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clipData = ClipData.newPlainText("YouTube Search URL", youtubeSearchUrl)
                            clipboardManager.setPrimaryClip(clipData)
                        }
                    )
                    TextView(
                        text = youtubeSearchUrl,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = BabyBlue,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif
                        ),
                        modifier = Modifier.padding(start = 5.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(youtubeSearchUrl)
                                intent.setPackage("com.google.android.youtube")
                                context.startActivity(intent)
                            }
                    )
                }

                TextView(
                    text = "Neymar da Silva Santos Júnior (born 5 February 1992), known as Neymar Júnior or mononymously as Neymar, is a Brazilian professional footballer who plays as a forward for Saudi Pro League club Al Hilal and the Brazil national team. A prolific goalscorer and playmaker, he is widely regarded as one of the best players in the world and the best Brazilian player of his generation.[3][4][5] Neymar has scored at least 100 goals for three different clubs, making him one of the few players to achieve this feat.",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Justify
                    ),
                    modifier = Modifier.padding(top = 20.dp)
                )
                TextView(
                    text = "Neymar da Silva Santos Júnior (born 5 February 1992), known as Neymar Júnior or mononymously as Neymar, is a Brazilian professional footballer who plays as a forward for Saudi Pro League club Al Hilal and the Brazil national team. A prolific goalscorer and playmaker, he is widely regarded as one of the best players in the world and the best Brazilian player of his generation.[3][4][5] Neymar has scored at least 100 goals for three different clubs, making him one of the few players to achieve this feat.",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Justify
                    ),
                    modifier = Modifier.padding(top = 20.dp)
                )
                TextView(
                    text = "Neymar da Silva Santos Júnior (born 5 February 1992), known as Neymar Júnior or mononymously as Neymar, is a Brazilian professional footballer who plays as a forward for Saudi Pro League club Al Hilal and the Brazil national team. A prolific goalscorer and playmaker, he is widely regarded as one of the best players in the world and the best Brazilian player of his generation.[3][4][5] Neymar has scored at least 100 goals for three different clubs, making him one of the few players to achieve this feat.",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Justify
                    ),
                    modifier = Modifier.padding(top = 20.dp)
                )
                TextView(
                    text = "Neymar finished third for the FIFA Ballon d'Or in 2015 and 2017, has been awarded the FIFA Puskás Award, has been named in the FIFA FIFPro World11 twice, and the UEFA Champions League Squad of the Season three times. Off the pitch, he ranks among the world's most prominent sportsmen.",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Justify
                    ),
                    modifier = Modifier.padding(top = 20.dp)
                )
                Spacer(modifier = Modifier.padding(bottom = 15.dp))
            }
        }
    }
}