package com.example.jamhacks2025

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun IndividualDm(navController: NavController, profileName: String, imageResId: Int) {
    var messageText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffdc9e82))
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
            text = "Chat with $profileName",
            color = Color(0xff151e3f),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 92.dp, top = 92.dp)
        )
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Profile Photo",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 162.dp)
                .size(120.dp)
                .clip(CircleShape)
        )
        Text(
            text = profileName,
            color = Color(0xff151e3f),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 305.dp)
        )
        Text(
            text = "$profileName seems to be a great fit for your team!",
            color = Color(0xfff2f3d9),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 358.dp)
        )
        // Message Input Area
        TextField(
            value = messageText,
            onValueChange = { messageText = it },
            placeholder = { Text("Type a message...") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xfff2f3d9),
                unfocusedContainerColor = Color(0xfff2f3d9)
            ),

                modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 36.dp, bottom = 32.dp)
                .width(300.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(10.dp))
        )

    }
}
