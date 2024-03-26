package com.example.myteffrenchapp.data

/**
 * Data class that represents the current UI state in terms of [quantity], [flavor],
 * [dateOptions], selected pickup [date] and [price]
 */
data class AppUiState(
    /** Selected cupcake quantity (1, 6, 12) */
    val quantity: Int = 0,
    /** Flavor of the cupcakes in the order (such as "Chocolate", "Vanilla", etc..) */
    val flavor: String = "",
    /** Selected date for pickup (such as "Jan 1") */
    val date: String = "",
    /** Word Pair to be translated */
    val answerWordPair: Pair<String, String> = Pair("", ""),
    /** Available pickup dates for the order*/
    val pickupOptions: List<String> = listOf(),
    /** Total score for this quiz i.e. questions/translations in quiz set */
    val totalScore: Int = 0,
    /** Correct answers so far in this quiz*/
    val currentScore: Int = 0,
    /** price cupcake str*/
    val subtotal: String = "subtotalNotApplicable"
)
