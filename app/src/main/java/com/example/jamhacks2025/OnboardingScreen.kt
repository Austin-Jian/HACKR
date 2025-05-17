package com.example.jamhacks2025

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.compose.ui.unit.sp

@Composable
fun AndroidCompact4(navController: NavController? = null) {
    var name by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffc16e70))
            .padding(24.dp)
    ) {
        Text(
            text = "Let’s get started!",
            color = Color(0xfff2f3d9),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.offset(y = 100.dp)
        )

        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Enter your name...") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xffdc9e82).copy(alpha = 0.4f),
                unfocusedContainerColor = Color(0xffdc9e82).copy(alpha = 0.4f)
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xfff2f3d9))
                .padding(8.dp)
                .fillMaxWidth(0.8f)
        )

        Button(
            onClick = { UserManager.userName = name
                navController?.navigate("profile_photo")
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff2f3d9)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
                .fillMaxWidth(0.8f)
                .height(60.dp)
        ) {
            Text("Next", color = Color(0xffdc9e82), fontSize = 20.sp)
        }

    }
}

@Preview
@Composable
fun OnboardingPreview() {
    AndroidCompact4()
}
@Composable
fun OnboardingScreen(navController: NavController) {
    AndroidCompact4(navController)
}

