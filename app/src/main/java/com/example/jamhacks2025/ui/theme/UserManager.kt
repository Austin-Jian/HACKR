package com.example.jamhacks2025

import android.net.Uri

object UserManager {
    var userName: String = ""
    var profilePhotoUri: Uri? = null
    var skills: List<String> = emptyList()          // User's own skills
    var partnerSkills: List<String> = emptyList()
    var messages: MutableMap<String, MutableList<String>> = mutableMapOf()
}