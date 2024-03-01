package com.ocirnexam.mindfree.todo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ocirnexam.mindfree.R
import com.ocirnexam.mindfree.todo.entities.Todo

class TodoAdapter(
    val todos: List<Todo>,
    private val viewModel: TodoViewModel
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val temp = 0
        holder.itemView.apply {
            findViewById<TextView>(R.id.todoTitle).text = todos[position].title
            findViewById<TextView>(R.id.todoDescription).text = todos[position].description
            findViewById<CheckBox>(R.id.todoCheckbox).isChecked = todos[position].isChecked
            findViewById<ImageButton>(R.id.btnDelete).setOnClickListener {
                val itemsToNotify = todos.size - position
                viewModel.onEvent(TodoEvent.DeleteTodo(todos[position]))
                notifyItemRangeChanged(position, itemsToNotify)
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}