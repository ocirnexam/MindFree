package com.ocirnexam.mindfree.todo.adddialog

import com.ocirnexam.mindfree.todo.entities.Todo

interface AddTodoItemDialogListener {
    fun onAddTodoItemButtonClicked(item: Todo)
}