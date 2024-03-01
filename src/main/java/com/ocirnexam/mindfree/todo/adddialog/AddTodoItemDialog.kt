package com.ocirnexam.mindfree.todo.adddialog

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.ocirnexam.mindfree.R
import com.ocirnexam.mindfree.todo.entities.Todo


class AddTodoItemDialog(context: Context, private var addTodoItemDialogListener: AddTodoItemDialogListener) : AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_todo_item)

        val btnAddTodo = findViewById<Button>(R.id.btnAddTodo)
        val btnCancel = findViewById<Button>(R.id.btnCancelTodo)
        btnAddTodo?.setOnClickListener {
            val title = findViewById<EditText>(R.id.etTodoTitle)
            val description = findViewById<EditText>(R.id.etTodoDescription)
            if (title?.text.toString().isBlank() || description?.text.toString().isBlank()) {
                Toast.makeText(context, "Please enter a Title and a Description for your Todo!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item = Todo(title?.text.toString(), description?.text.toString(), false)
            addTodoItemDialogListener.onAddTodoItemButtonClicked(item)
            dismiss()
        }
        btnCancel?.setOnClickListener {
            cancel()
        }
    }
}