package com.example.swipequiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class QuestionAdapter(private val questions: List<Question>): RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    // creates the view for a question
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    // changes the content in the view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvQuestion: TextView = itemView.findViewById(android.R.id.text1)
        var answer = false

        init {
            itemView.setOnClickListener {
                Snackbar.make(tvQuestion, "Answer is: $answer", Snackbar.LENGTH_SHORT).show()
            }
        }

        fun bind(question: Question) {
            answer = question.answer
            tvQuestion.text = question.question
        }
    }
}