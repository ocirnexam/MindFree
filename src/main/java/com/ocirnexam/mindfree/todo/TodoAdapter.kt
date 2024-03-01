package com.ocirnexam.mindfree.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ocirnexam.mindfree.R

class TodoAdapter(
    val viewModel: TodoViewModel
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemview: View): RecyclerView.ViewHolder(itemview)

    var todos: List<Todo> = viewModel.state.value.todos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.todoTitle).text = todos[position].title
            findViewById<CheckBox>(R.id.todoCheckbox).isChecked = todos[position].isChecked
            findViewById<ImageButton>(R.id.btnDelete).setOnClickListener {
                viewModel.onEvent(TodoEvent.DeleteTodo(todos[position]))
                notifyItemRemoved(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}