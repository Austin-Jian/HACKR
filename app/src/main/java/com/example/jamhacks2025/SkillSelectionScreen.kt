package com.example.jamhacks2025

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SkillSelectionScreen(navController: NavController) {
    val categories = listOf("frontend", "backend", "hardware", "software", "design", "python", "C#")
    val selectedCategories = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffdc9e82))
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Choose Your Skills",
            fontSize = 28.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 40.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            categories.chunked(2).forEach { rowItems ->
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    rowItems.forEach { category ->
                        SkillChip(
                            text = category,
                            isSelected = selectedCategories.contains(category),
                            onClick = {
                                if (selectedCategories.contains(category)) {
                                    selectedCategories.remove(category)
                                } else {
                                    selectedCategories.add(category)
                                }
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }

                }
            }
        }

        Button(
            onClick = {
                UserManager.skills = selectedCategories.toList()
                navController.navigate("home")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff2f3d9)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .height(60.dp)
        ) {
            Text("I'm Done", color = Color(0xffdc9e82), fontSize = 20.sp)
        }
    }
}

@Composable
fun SkillChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier // Add this
) {
    val backgroundColor = if (isSelected) Color(0xfff2f3d9) else Color.Transparent
    val textColor = if (isSelected) Color(0xffdc9e82) else Color(0xfff2f3d9)

    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = modifier // Use passed modifier here
            .height(50.dp)
    ) {
        Text(text, color = textColor)
    }
}

