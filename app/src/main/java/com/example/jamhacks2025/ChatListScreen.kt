package com.example.jamhacks2025

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar

data class ChatProfile(val name: String, val imageResId: Int)

val sampleProfiles = listOf(
    ChatProfile("John Doe", R.drawable.p1),
    ChatProfile("Anne Smith", R.drawable.p2),
    ChatProfile("Charlie Brown", R.drawable.p3),
    ChatProfile("Dana Lee", R.drawable.p4),
    ChatProfile("Ethan Clark", R.drawable.p5),
    ChatProfile("Fiona Gallagher", R.drawable.p6),
    ChatProfile("George King", R.drawable.p7),
    ChatProfile("Hannah Miles", R.drawable.p8),
    ChatProfile("Ian Wright", R.drawable.p9),
    ChatProfile("Julia Roberts", R.drawable.p10),
    ChatProfile("Kevin Nguyen", R.drawable.p11),
    ChatProfile("Luna Park", R.drawable.p12),
    ChatProfile("Mike Jordan", R.drawable.p13),
    ChatProfile("Nina Patel", R.drawable.p14),
    ChatProfile("Oscar Wilde", R.drawable.p15),
    ChatProfile("Paula Adams", R.drawable.p16),
    ChatProfile("Quinn Taylor", R.drawable.p17),
    ChatProfile("Sam Carter", R.drawable.p19),
    ChatProfile("Tina Chen", R.drawable.p20)
)


@Composable
fun ChatListScreen(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xffdc9e82))
    ) {
        Text(
            text = "HACKR",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFFFFF0D5),
            modifier = Modifier.offset(x = 52.dp, y = 56.dp)
        )
        Text(
            text = "Your Chats",
            fontSize = 50.sp,
            style = MaterialTheme.typography.displayLarge,
            color = Color(0xFF151E3F),
            modifier = Modifier.offset(x = 52.dp, y = 120.dp)
        )

        LazyColumn(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 220.dp, start = 52.dp, end = 52.dp)
        ) {
            val matches = sampleProfiles.filter { profile ->
                swipeStatus[profile.name] == true && userSwipes.contains(profile.name)
            }

            items(matches) { profile ->
                ChatProfileItem(profile, navController)
            }
        }
    }
}

@Composable
fun ChatProfileItem(profile: ChatProfile, navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable {
                navController.navigate("chat_dm/${profile.name}/${profile.imageResId}")
            }
    ) {
        Image(
            painter = painterResource(id = profile.imageResId),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = profile.name,
            color = Color(0xff151e3f),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
