package com.example.jamhacks2025

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfilePhotoScreen(navController: NavController? = null) {
    AndroidCompact2(modifier = Modifier, navController)
}

@Composable
fun AndroidCompact2(modifier: Modifier = Modifier, navController: NavController? = null) {
    Box(
        modifier = modifier
            .requiredWidth(412.dp)
            .requiredHeight(917.dp)
            .background(Color(0xffc16e70))
    ) {
        // Title Text
        Text(
            text = "Add a profile photo",
            color = Color(0xfff2f3d9),
            style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 56.dp, y = 153.dp)
        )

        // Profile Placeholder Circle with Plus Icon
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 86.dp, y = 306.dp)
                .size(240.dp)
                .clip(CircleShape)
                .background(Color(0xfff2f3d9))
        ) {
            Text(
                text = "+",
                color = Color(0xff151e3f), // Navy Blue
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Description Text
        Text(
            text = "This is how you’ll show up to others.",
            color = Color(0xfff2f3d9),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 56.dp, y = 599.dp)
        )

        // "Next" Button
        Button(
            onClick = { navController?.navigate("home") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff2f3d9)),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 56.dp, y = 778.dp)
                .size(width = 300.dp, height = 80.dp)
        ) {
            Text(
                text = "Next",
                color = Color(0xffdc9e82),
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(widthDp = 412, heightDp = 917)
@Composable
private fun AndroidCompact2Preview() {
    AndroidCompact2()
}
