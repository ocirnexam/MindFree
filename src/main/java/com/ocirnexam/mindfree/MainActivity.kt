package com.ocirnexam.mindfree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ocirnexam.mindfree.todo.adddialog.AddTodoItemDialog
import com.ocirnexam.mindfree.todo.adddialog.AddTodoItemDialogListener
import com.ocirnexam.mindfree.todo.entities.Todo
import com.ocirnexam.mindfree.todo.ui.TodoAdapter
import com.ocirnexam.mindfree.todo.ui.TodoEvent
import com.ocirnexam.mindfree.todo.ui.TodoViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoViewModel by viewModels<TodoViewModel>()

        val adapter = TodoAdapter(todoViewModel)
        val rvTodo = findViewById<RecyclerView>(R.id.rvTodo)

        rvTodo.adapter = adapter
        rvTodo.layoutManager = LinearLayoutManager(this)

        Log.d("TEST", "Before Button called")
        val btnNewTodo = findViewById<FloatingActionButton>(R.id.btnNewTodo)
        btnNewTodo.setOnClickListener {
            AddTodoItemDialog(this, object : AddTodoItemDialogListener {
                override fun onAddTodoItemButtonClicked(item: Todo) {
                    todoViewModel.onEvent(TodoEvent.SetTodoText(item.title))
                    todoViewModel.onEvent(TodoEvent.SetTodoDescription(item.description))
                    todoViewModel.onEvent(TodoEvent.SaveTodo)
                    adapter.notifyItemInserted(0)
                }
            }).show()
        }
    }
}