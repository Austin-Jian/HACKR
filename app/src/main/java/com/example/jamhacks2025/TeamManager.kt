package com.example.jamhacks2025

import androidx.compose.runtime.mutableStateListOf

object TeamManager {
    var userImageResId: Int = R.drawable.default_profile // Default image at app start

    fun setUserImage(imageResId: Int) {
        userImageResId = imageResId
    }

    private val _teamMembers = mutableStateListOf<Pair<String, Int>>()
    val teamMembers: List<Pair<String, Int>> get() = _teamMembers

    fun addTeamMember(name: String, imageResId: Int) {
        if (_teamMembers.size < 3) {
            _teamMembers.add(Pair(name, imageResId))
        }
    }
}
