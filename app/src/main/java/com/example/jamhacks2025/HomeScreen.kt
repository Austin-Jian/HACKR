package com.example.jamhacks2025

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFFC27575),
        contentColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        if (dragAmount < -50) {
                            navController.navigate("partner_skills")
                        }
                    }
                },
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "HACKR",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFFFFF0D5),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 30.dp, top = 20.dp)
                    )

                    UserManager.profilePhotoUri?.let { uri ->
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(60.dp)
                                .align(Alignment.TopCenter)
                                .offset(x = 120.dp)
                                .clip(CircleShape), // Adds some padding to the right and top
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Hi ${UserManager.userName},",
                    fontSize = 50.sp,
                    style = MaterialTheme.typography.displayLarge,
                    color = Color(0xFF151E3F),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .padding(start = 30.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .padding(start = 30.dp)
                ) {
                    UserManager.skills.forEach { skill ->
                        StaticSkillChip(color1 = Color(0xFFFFF0D5), label = skill)
                    }
                }

                SectionCard(
                    title = "Your Team",
                    count = "${TeamManager.teamMembers.size + 1}/4",
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        TeamManager.teamMembers.forEach { (name, imageResId) ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painter = painterResource(id = imageResId),
                                    contentDescription = "Team Member Photo",
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFD9A57C)),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = name, fontSize = 12.sp, color = Color.Black)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                SectionCard(
                    title = "Find a Hacker",
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        UserManager.partnerSkills.forEach { skill ->
                            StaticSkillChip(color1 = Color(0xFFD9A57C), label = skill)
                        }
                    }

                    if (TeamManager.teamMembers.size < 3) {
                        IconButton(
                            onClick = { navController.navigate("swipe") },
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.End)
                                .background(Color(0xFF151E3F), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowForward,
                                contentDescription = "Find a Hacker",
                                tint = Color(0xFFF5F5DC)
                            )
                        }
                    } else {
                        Text(
                            text = "Team is Full!",
                            color = Color(0xFF151E3F),
                            fontSize = 14.sp,
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(end = 8.dp, top = 8.dp)
                        )
                    }
                }
            }

            Button(
                onClick = { navController.navigate("chat_list") },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff2f3d9)),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 28.dp)
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Your Chats",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFFD9A57C)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Filled.ArrowForward, contentDescription = "Resume", tint = Color(0xFFD9A57C))
                }
            }
        }
    }
}

@Composable
fun StaticSkillChip(color1: Color, label: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = Color.Transparent,
        border = BorderStroke(1.dp, color1),
        modifier = Modifier.height(32.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Text(text = label, color = color1, fontSize = 12.sp)
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
