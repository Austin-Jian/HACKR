package com.example.jamhacks2025

import androidx.compose.foundation.background
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Arrangement


import androidx.navigation.NavController

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PartnerSkillsScreen(navController: NavController) {
    val categories = listOf("frontend", "backend", "hardware", "software", "C#", "design", "python")
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
            text = "What are \nyou looking for?",
            style = MaterialTheme.typography.displayLarge,
            color = Color(0xfff2f3d9),
            fontSize = 50.sp,
            modifier = Modifier.offset(y = 170.dp, x=40.dp)
        )

        FlowRow(
            modifier = Modifier.padding(start = 32.dp, end = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            categories.forEach { category ->
                SkillChi(
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
        Button(
            onClick = {
                navController.navigate("home")
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff2f3d9)),
            modifier = Modifier
                //.align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
                .fillMaxWidth(0.8f)
                .height(60.dp)
        ) {
            Text("Find a hacker!", style = MaterialTheme.typography.bodyLarge, color = Color(0xffdc9e82))
        }

    }
}

@Composable
fun SkillChi(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) Color(0xfff2f3d9) else Color.Transparent
    val textColor = if (isSelected) Color(0xffdc9e82) else Color(0xfff2f3d9)
    val borderColor = Color(0xfff2f3d9)

    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = backgroundColor),
        modifier = modifier
            .wrapContentWidth()
    ) {
        Text(text, color = textColor)
    }
}


