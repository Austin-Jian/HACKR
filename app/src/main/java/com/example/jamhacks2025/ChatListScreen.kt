package com.example.jamhacks2025
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ChatProfile(val name: String, val imageResId: Int)

val sampleProfiles = listOf(
    ChatProfile("John Doe", R.drawable.ellipse3),
    ChatProfile("Anne Smith", R.drawable.ellipse4),
    ChatProfile("Charlie Brown", R.drawable.ellipse3),
    ChatProfile("Dana Lee", R.drawable.ellipse4)
)

@Composable
fun ChatListScreen(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xffdc9e82))
    ) {
        Text(
            text = "hackr",
            color = Color(0xfff2f3d9),
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 52.dp, top = 35.dp)
        )
        Text(
            text = "Your Chats",
            color = Color(0xff151e3f),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 51.dp, top = 92.dp)
        )

        LazyColumn(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 180.dp, start = 20.dp, end = 20.dp)
        ) {
            items(sampleProfiles) { profile ->
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
                .clip(CircleShape)
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


@Preview(widthDp = 412, heightDp = 917)
@Composable
private fun ChatListScreenPreview() {
    val fakeNavController = rememberNavController()
    ChatListScreen(fakeNavController)
}
