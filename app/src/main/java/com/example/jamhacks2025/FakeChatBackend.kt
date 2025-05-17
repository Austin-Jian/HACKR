package com.example.jamhacks2025

data class Message(
    val sender: String,
    val content: String,
    val timestamp: String
)

data class MatchProfile(
    val id: String,
    val name: String,
    val profileImageRes: Int
)

object FakeChatBackend {
    private val chatHistories = mutableMapOf<String, MutableList<Message>>()
    private val swipeStatus = mutableMapOf<String, MutableSet<String>>() // userId -> swiped-right userIds
    private val userMatches = mutableMapOf<String, MutableList<MatchProfile>>()

    init {
        swipeStatus["user_2"] = mutableSetOf("user_1") // Bob swiped right on Alice
        swipeStatus["user_3"] = mutableSetOf("user_1") // Charlie swiped right on Alice
    }


    fun getMatchesForUser(userId: String): List<MatchProfile> {
        return userMatches[userId] ?: emptyList()
    }

    fun getAlreadySwiped(currentUserId: String): MutableSet<String> {
        return swipeStatus.getOrPut(currentUserId) { mutableSetOf() }
    }

    fun swipeRight(currentUserId: String, targetUserId: String) {
        val acceptedByCurrent = swipeStatus.getOrPut(currentUserId) { mutableSetOf() }
        acceptedByCurrent.add(targetUserId)

        val acceptedByTarget = swipeStatus.getOrPut(targetUserId) { mutableSetOf() }

        if (currentUserId in acceptedByTarget) {
            val currentUserMatches = userMatches.getOrPut(currentUserId) { mutableListOf() }
            if (currentUserMatches.none { it.id == targetUserId }) {
                val user = FakeUserDatabase.users.find { it.id == targetUserId }
                user?.let {
                    currentUserMatches.add(MatchProfile(it.id, it.name, it.profileImageRes))
                    chatHistories.getOrPut(chatId(currentUserId, it.id)) { mutableListOf() }
                }
            }
        }
    }

    fun swipeLeft(currentUserId: String, targetUserId: String) {
        swipeStatus.getOrPut(currentUserId) { mutableSetOf() }.add(targetUserId)
    }

    fun getMatchById(matchId: String): MatchProfile? {
        return FakeUserDatabase.users.find { it.id == matchId }?.let {
            MatchProfile(it.id, it.name, it.profileImageRes)
        }
    }

    fun getMessages(matchId: String): List<Message> {
        return chatHistories[chatId("user_1", matchId)] ?: emptyList()
    }

    fun sendMessage(matchId: String, message: String) {
        chatHistories.getOrPut(chatId("user_1", matchId)) { mutableListOf() }
            .add(Message("Me", message, "Now"))
    }

    private fun chatId(user1: String, user2: String): String =
        listOf(user1, user2).sorted().joinToString(":")
}
