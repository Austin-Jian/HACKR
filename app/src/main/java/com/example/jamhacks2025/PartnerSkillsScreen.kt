package com.example.jamhacks2025

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PartnerSkillsScreen(navController: NavController) {
    val categories = listOf("frontend", "backend", "hardware", "software", "design", "python", "C#")
    val selectedCategories = remember { mutableStateListOf<String>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xffdc9e82))
    ) {
        Text(
            text = "What are you looking for?",
            style = TextStyle(fontSize = 32.sp),
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            categories.chunked(2).forEach { rowItems ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowItems.forEach { category ->
                        SelectableButton(
                            text = category,
                            isSelected = selectedCategories.contains(category),
                            onClick = {
                                if (selectedCategories.contains(category)) {
                                    selectedCategories.remove(category)
                                } else {
                                    selectedCategories.add(category)
                                }
                            }
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                UserManager.partnerSkills = selectedCategories.toList()
                navController.navigate("home") {
                    popUpTo("onboarding")
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff2f3d9)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp)
                .width(300.dp)
                .height(80.dp)
        ) {
            Text(
                text = "I'm done",
                color = Color(0xffdc9e82),
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium)
            )
        }
    }
}
@Composable
fun SelectableButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color(0xfff2f3d9) else Color.Transparent
    val textColor = if (isSelected) Color(0xffdc9e82) else Color(0xfff2f3d9)

    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        contentPadding = PaddingValues(10.dp),
        border = BorderStroke(2.dp, Color(0xfff2f3d9)),
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()


    ) {
        Text(
            text = text,
            color = textColor,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
        )
    }
}
