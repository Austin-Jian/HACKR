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
    val matches = listOf(
        MatchProfile("match_1", "Alex", R.drawable.placeholder_profile),
        MatchProfile("match_2", "Jamie", R.drawable.placeholder_profile),
        MatchProfile("match_3", "Taylor", R.drawable.placeholder_profile)
    )

    private val chatHistories = mutableMapOf<String, MutableList<Message>>()

    init {
        chatHistories["match_1"] = mutableListOf(
            Message("System", "🎉 You have been matched with Alex!", "Now")
        )
        chatHistories["match_2"] = mutableListOf()
        chatHistories["match_3"] = mutableListOf(
            Message("System", "🎉 You have been matched with Taylor!", "Now")
        )
    }

    fun getMatchById(matchId: String): MatchProfile? {
        return matches.find { it.id == matchId }
    }

    fun getMessages(matchId: String): List<Message> {
        return chatHistories[matchId] ?: emptyList()
    }

    fun sendMessage(matchId: String, message: String) {
        chatHistories.getOrPut(matchId) { mutableListOf() }
            .add(Message("Me", message, "Now"))
    }
}
