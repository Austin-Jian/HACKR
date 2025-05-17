package com.example.jamhacks2025
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material3.ExperimentalMaterial3Api
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JamHacks2025Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController) }
                    composable("matches") { MatchesScreen(navController) }
                    composable("chat/{matchId}") { backStackEntry ->
                        val matchId = backStackEntry.arguments?.getString("matchId") ?: ""
                        ChatScreen(navController, matchId)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchesScreen(navController: NavController) {
    val matches = FakeChatBackend.matches

    Scaffold(
        topBar = { TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Swipe Hint",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Your Matches")
                }
            }
        )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        if (dragAmount > 50) {
                            navController.navigate("home")
                        }
                    }
                }
        ) {
            items(matches) { match ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { navController.navigate("chat/${match.id}") }
                ) {
                    Image(
                        painter = painterResource(id = match.profileImageRes),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = match.name, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ChatScreen(navController: NavController, matchId: String) {
    val match = FakeChatBackend.getMatchById(matchId) ?: return
    var messageText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Message>().apply {
        addAll(FakeChatBackend.getMessages(matchId))
    } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat with ${match.name}") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type a message...") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (messageText.isNotBlank()) {
                        FakeChatBackend.sendMessage(matchId, messageText.trim())
                        messages.add(Message("Me", messageText.trim(), "Now"))
                        messageText = ""
                    }
                }) {
                    Text("Send")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        if (dragAmount > 50) {
                            navController.navigate("home")
                        }
                    }
                }
        ) {
            // Profile Header
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = match.profileImageRes),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(80.dp).clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Matched with ${match.name}", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
                reverseLayout = true
            ) {
                items(messages.reversed()) { message ->
                    MessageBubble(message)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun MessageBubble(message: Message) {
    val bubbleColor = if (message.sender == "Me")
        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
    else
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.sender == "Me") Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = bubbleColor,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = message.content,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}