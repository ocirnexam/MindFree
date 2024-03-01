package com.ocirnexam.mindfree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ocirnexam.mindfree.todo.Todo
import com.ocirnexam.mindfree.todo.TodoAdapter
import com.ocirnexam.mindfree.todo.TodoEvent
import com.ocirnexam.mindfree.todo.TodoViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoViewModel = TodoViewModel()

        val adapter = TodoAdapter(todoViewModel)
        val rvTodo = findViewById<RecyclerView>(R.id.rvTodo)
        val etNewTodo = findViewById<EditText>(R.id.etNewTodo)

        rvTodo.adapter = adapter
        rvTodo.layoutManager = LinearLayoutManager(this)

        etNewTodo.addTextChangedListener {
            todoViewModel.onEvent(TodoEvent.SetTodoText(it.toString()))
        }

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            todoViewModel.onEvent(TodoEvent.SaveTodo)
            etNewTodo.setText("")
            adapter.notifyItemInserted(0)
        }
    }
}