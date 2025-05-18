package com.example.jamhacks2025

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import android.os.Bundle
import com.example.jamhacks2025.ProfilePhotoScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.ui.unit.IntOffset
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jamhacks2025.ui.theme.JamHacks2025Theme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalDensity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt
val possibleSkills = listOf("frontend", "backend", "hardware", "software", "C#", "design", "python")
val profileSkills = sampleProfiles.associate { profile ->
    profile.name to possibleSkills.shuffled().take((1..3).random())
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            JamHacks2025Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "launch") {
                    composable("launch") { LaunchScreen(navController) }
                    composable("onboarding") { OnboardingScreen(navController) }
                    composable("profile_photo") { ProfilePhotoScreen(navController) }
                    composable("home") { HomeScreen(navController) }
                    composable("swipe") { SwipeScreen(navController) }
                    composable("chat_list") { ChatListScreen(navController) }

                    composable("chat_dm/{profileName}/{imageResId}") { backStackEntry ->
                        val profileName = backStackEntry.arguments?.getString("profileName") ?: ""
                        val imageResId = backStackEntry.arguments?.getString("imageResId")?.toInt() ?: 0
                        IndividualDm(navController, profileName, imageResId)
                    }
                    composable("skill_selection") { SkillSelectionScreen(navController) }
                    composable("partner_skills") { PartnerSkillsScreen(navController) }

                }
            }
        }
    }
}

val permanentlySwipedProfiles = mutableSetOf<String>()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeScreen(navController: NavController) {
    val scope = rememberCoroutineScope()

    val profiles = remember {
        sampleProfiles.filter { profile ->
            profile.name !in permanentlySwipedProfiles &&
                    (UserManager.partnerSkills.isEmpty() ||
                            profileSkills[profile.name]?.any { it in UserManager.partnerSkills } == true)
        }.toMutableStateList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffc16e70))
            .padding(top = 24.dp) // Adjust padding as needed
    ) {
        // Custom Top Bar using a Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Add horizontal padding if needed
        ) {
            // Back Button
            IconButton(onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .offset(x=24.dp, y=60.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow),  // Replace with your back icon
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp).rotate(180f),
                    tint = Color(0xff151e3f) // Set icon color
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Title Text
            Text(
                text = "Find Teammates",
                color = Color(0xff151e3f),
                style = MaterialTheme.typography.displayLarge,
                fontSize = 24.sp,
                modifier = Modifier.offset(x = 20.dp, y=60.dp)
            )
        }

        // Main Content
        if (profiles.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No more profiles!", style = MaterialTheme.typography.displayMedium, color = Color(0xff151e3f),)
            }
        } else {
            val profile = profiles.first()
            val offsetX = remember(profile) { Animatable(0f) }
            val screenWidth = with(LocalDensity.current) { 300.dp.toPx() }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xfff2f3d9),
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .aspectRatio(0.75f)
                        .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                        .pointerInput(profile) {
                            detectHorizontalDragGestures(
                                onDragEnd = {
                                    scope.launch {
                                        when {
                                            offsetX.value > screenWidth / 3 -> {
                                                offsetX.animateTo(screenWidth, tween(300))
                                                delay(200)
                                                userSwipes.add(profile.name)
                                                permanentlySwipedProfiles.add(profile.name)
                                                profiles.remove(profile)
                                                if (swipeStatus[profile.name] == true) {
                                                    navController.navigate("chat_dm/${profile.name}/${profile.imageResId}")
                                                }
                                            }
                                            offsetX.value < -screenWidth / 3 -> {
                                                offsetX.animateTo(-screenWidth, tween(300))
                                                delay(200)
                                                permanentlySwipedProfiles.add(profile.name)
                                                profiles.remove(profile)
                                            }
                                            else -> {
                                                offsetX.animateTo(0f, tween(300))
                                            }
                                        }
                                    }
                                },
                                onHorizontalDrag = { _, dragAmount ->
                                    scope.launch { offsetX.snapTo(offsetX.value + dragAmount) }
                                }
                            )
                        }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize().padding(60.dp)
                    ) {
                        Image(
                            painter = painterResource(id = profile.imageResId),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(150.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = profile.name, style = MaterialTheme.typography.displayLarge, fontSize = 25.sp, color = Color(0xffdc9e82))
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),

                            modifier = Modifier
                                .padding(bottom = 24.dp, top = 12.dp)
                                //.padding(start = 30.dp)
                                .zIndex(1f),
                        ) {
                            // Display skills as chips for the current profile
                            profileSkills[profile.name]?.forEach { skill ->
                                StaticSkillChi(
                                    color1 = Color(0xffc16e70), // Custom color for chips
                                    label = skill
                                )
                            }
                        }
                        Text(
                            text = "Swipe right if interested or left if you want to keep looking",
                            color = Color(0xff151e3f),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .zIndex(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StaticSkillChi(color1: Color, label: String) {
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

