package com.ocirnexam.mindfree.todo

data class TodoState (
    val todoText: String = "",
    val todos: List<Todo> = emptyList()
)