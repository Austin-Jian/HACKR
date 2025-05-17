package com.example.jamhacks2025
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFFC27575), // Full background color
        contentColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        if (dragAmount < -50) { // Left swipe detected
                            navController.navigate("partner_skills")
                        }
                    }
                },
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "HACKR",
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 20.sp,
                    modifier = Modifier.offset(x=36.dp, y=56.dp),
                    color = Color(0xFFFFF0D5) // Light cream text color
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Hi ${UserManager.userName},",
                    fontSize = 50.sp,
                    style = MaterialTheme.typography.displayLarge,
                    color = Color(0xFF151E3F),
                    modifier = Modifier.offset(y = 60.dp, x=36.dp)
                )


                Spacer(modifier = Modifier.height(80.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.offset(x=36.dp)) {
                    UserManager.skills.forEach { skill ->
                        AssistChip(label = skill)
                    }
                }


                Spacer(modifier = Modifier.height(24.dp))

                // Your Team Section
                SectionCard(title = "Your Team", count = "3/4", modifier = Modifier.padding(start = 32.dp, end = 32.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        repeat(2) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFD9A57C))
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "John Doe", fontSize = 12.sp, color = Color.Black)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Keep Looking Section
                SectionCard(title = "Keep Looking", modifier = Modifier.padding(start = 32.dp, end = 32.dp)) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        items(2) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFD9A57C))
                            )
                        }
                        item {
                            IconButton(onClick = { navController.navigate("swipe") }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowForward,
                                    contentDescription = "Keep Matching",
                                    tint = Color(0xFF1D1D1B),
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(Color(0xFFF5F5DC), CircleShape)
                                )
                            }
                        }
                    }
                }
            }

            // Resume a Chat Button
            Button(
                onClick = { navController.navigate("chat_list") },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff2f3d9)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 28.dp)
                    .fillMaxWidth(0.8f)
                    .height(60.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Resume a chat",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFFD9A57C),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Filled.ArrowForward, contentDescription = "Resume", tint = Color(0xFFD9A57C))
                }
            }
        }
    }
}

@Composable
fun AssistChip(label: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = Color.Transparent,
        border = BorderStroke(1.dp, Color(0xFFFFF0D5)),
        modifier = Modifier
            .height(32.dp)
            .padding(end = 8.dp)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 12.dp)) {
            Text(text = label, color = Color(0xFFFFF0D5), fontSize = 12.sp)
        }
    }
}

@Composable
fun SectionCard(
    modifier: Modifier = Modifier,
    title: String,
    count: String = "",
    content: @Composable () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFF5F5DC),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                if (count.isNotEmpty()) {
                    Text(
                        text = count,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}

