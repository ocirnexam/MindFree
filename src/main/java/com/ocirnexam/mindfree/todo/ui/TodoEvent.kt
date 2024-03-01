package com.ocirnexam.mindfree.todo.ui

import com.ocirnexam.mindfree.todo.entities.Todo

sealed interface TodoEvent {
    object SaveTodo: TodoEvent
    data class SetTodoText(val todoText: String): TodoEvent
    data class SetTodoDescription(val todoDescription: String): TodoEvent
    data class DeleteTodo(val todo: Todo): TodoEvent
}