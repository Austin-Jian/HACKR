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
            style = MaterialTheme.typography.displayLarge,
            color = Color(0xfff2f3d9),
            fontSize = 50.sp,
            modifier = Modifier.offset(y = 160.dp, x=40.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Enter your name...", style = MaterialTheme.typography.bodyLarge, color = Color(0xffdc9e82)) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xfff2f3d9).copy(alpha = 0.4f),
                    unfocusedContainerColor = Color(0xfff2f3d9).copy(alpha = 0.4f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xfff2f3d9))
                    .padding(8.dp)
                    .fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(24.dp)) // Add spacing between TextField and Text

            Text(
                text = "What should others know you as?",
                color = Color(0xfff2f3d9),
                fontSize = 16.sp
            )
        }


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
            Text("Next", style = MaterialTheme.typography.bodyLarge, color = Color(0xffdc9e82))
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

