package com.example.jamhacks2025

val userId = "user_1"

// Tracks who already swiped right on us
val swipeStatus = mutableMapOf(
    "John Doe" to true,
    "Anne Smith" to true,
    "Charlie Brown" to false,
    "Dana Lee" to false,
    "Ethan Clark" to true,
    "Fiona Gallagher" to false,
    "George King" to true,
    "Hannah Miles" to false,
    "Ian Wright" to true,
    "Julia Roberts" to false,
    "Kevin Nguyen" to false,
    "Luna Park" to true,
    "Mike Jordan" to false,
    "Nina Patel" to true,
    "Oscar Wilde" to false,
    "Paula Adams" to true,
    "Quinn Taylor" to false,
    "Sam Carter" to false,
    "Tina Chen" to true
)

// Tracks who we have swiped right on
val userSwipes = mutableSetOf<String>()
