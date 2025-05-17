package com.example.jamhacks2025

data class Message(
    val sender: String, // "Me" or "Them"
    val content: String,
    val timestamp: String
)

object FakeChatBackend {
    val chatHistories = mutableMapOf<String, MutableList<Message>>()

    init {
        chatHistories["match_1"] = mutableListOf(
            Message("System", "🎉 You have been matched with this person!", "Now")
        )

        chatHistories["match_2"] = mutableListOf() // This remains empty if needed
    }

    fun sendMessage(matchId: String, message: String) {
        chatHistories.getOrPut(matchId) { mutableListOf() }
            .add(Message("Me", message, "Now"))
    }

    fun getMessages(matchId: String): List<Message> {
        return chatHistories[matchId] ?: emptyList()
    }
}
