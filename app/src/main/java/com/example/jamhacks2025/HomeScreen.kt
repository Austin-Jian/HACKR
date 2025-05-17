package com.example.jamhacks2025
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "app name",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(0xFFFFF0D5) // Light cream text color
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Hi ${UserManager.userName},",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFF0D5)
                )


                Spacer(modifier = Modifier.height(16.dp))

                // Filters Section
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("backend", "backend", "abc").forEach { label ->
                        AssistChip(label = label)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Your Team Section
                SectionCard(title = "Your Team", count = "3/4") {
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
                SectionCard(title = "Keep Looking") {
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
                onClick = { navController.navigate("matches") },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5F5DC)),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Resume a chat",
                        color = Color(0xFFD9A57C),
                        fontWeight = FontWeight.Bold
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
fun SectionCard(title: String, count: String = "", content: @Composable () -> Unit) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFF5F5DC),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
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
                    Text(text = count, fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}
