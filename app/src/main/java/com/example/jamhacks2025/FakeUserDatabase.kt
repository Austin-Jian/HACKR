package com.example.jamhacks2025

data class UserProfile(
    val id: String,
    val name: String,
    val skills: List<String>,
    val bio: String,
    val profileImageRes: Int
)

object FakeUserDatabase {
    val users = listOf(
        UserProfile("user_2", "Alice", listOf("Android", "UI/UX"), "Looking for a design-focused hackathon team!", R.drawable.placeholder_profile),
        UserProfile("user_3", "Bob", listOf("Backend", "AI"), "Passionate about AI and cloud services.", R.drawable.placeholder_profile),
        UserProfile("user_4", "Charlie", listOf("iOS", "AR/VR"), "Love building innovative AR experiences.", R.drawable.placeholder_profile)
    )
}
