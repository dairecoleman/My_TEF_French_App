package com.example.myteffrenchapp.data

import com.example.myteffrenchapp.R

object DataSource {


    // list of different quizzes user can pick from the app's start screen
    val test_type_options = listOf(
        Pair(R.string.first_vocab_set, 1)
    )
    // French>English Vocab to use in quiz
    val testOptions = listOf(
        Pair("Cependant", "However"),
        Pair("Malgré", "Despite"),
        Pair("Pourtant", "Yet"),
        Pair("Mais", "But")
    )
    val flavors = listOf(
        R.string.vanilla,
        R.string.chocolate
    )
}