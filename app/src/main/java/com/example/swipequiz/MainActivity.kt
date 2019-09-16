package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var questions = arrayListOf<Question>()
    private var questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerview()
        setQuestions()
    }

    private fun initRecyclerview() {
        rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter
        rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        // add swipe actions
        createItemTouchHelper().attachToRecyclerView(rvQuestions)

        // add click action
        rvQuestions.setRecyclerListener {

        }
    }

    private fun setQuestions() {
        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWERS[i]))
        }
        questionAdapter.notifyDataSetChanged()
    }

    private fun evaluateQuestion(position: Int, direction: Int) {
        if (direction == ItemTouchHelper.LEFT && Question.ANSWERS[position]) { // answer is correct on left swipe
            Snackbar.make(tvFeedback, getString(R.string.correct), Snackbar.LENGTH_SHORT).show()
        } else if (direction == ItemTouchHelper.RIGHT && !Question.ANSWERS[position]) { // answer is correct on right swipe
            Snackbar.make(tvFeedback, getString(R.string.correct), Snackbar.LENGTH_SHORT).show()
        } else { // answer is wrong
            Snackbar.make(tvFeedback, getString(R.string.incorrect), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // gets the item that is swiped
                val position = viewHolder.adapterPosition
                evaluateQuestion(position, direction)

                // reset the item to in original spot
                questionAdapter.notifyItemChanged(viewHolder.adapterPosition)
                questionAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}
