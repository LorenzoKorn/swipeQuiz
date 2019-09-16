package com.example.swipequiz

data class Question(var question: String, var answer: Boolean) {

    companion object {
        val QUESTIONS = arrayOf(
            "A 'var' and 'val' are the same",
            "Mobile application development grants 12 ETCS",
            "A Unit in kotlin corresponds to void in Java",
            "In kotlin the 'when' replaces the 'switch' operator in Java"
        )

        val ANSWERS = arrayOf(
            false,
            false,
            true,
            true
        )
    }
}