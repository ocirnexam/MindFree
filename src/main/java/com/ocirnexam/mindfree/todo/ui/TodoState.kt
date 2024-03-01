package com.ocirnexam.mindfree.todo.ui

import com.ocirnexam.mindfree.todo.entities.Todo

data class TodoState (
    val todoText: String = "",
    val todoDescription: String = "",
    val todos: List<Todo> = emptyList()
)