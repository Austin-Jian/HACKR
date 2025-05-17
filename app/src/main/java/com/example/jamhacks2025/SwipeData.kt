package com.example.jamhacks2025

val userId = "user_1"

// Tracks who already swiped right on us
val swipeStatus = mutableMapOf(
    "John Doe" to true,
    "Anne Smith" to true,
    "Charlie Brown" to false,
    "Dana Lee" to false
)

// Tracks who we have swiped right on
val userSwipes = mutableSetOf<String>()
