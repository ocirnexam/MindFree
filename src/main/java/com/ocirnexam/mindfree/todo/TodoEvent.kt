package com.ocirnexam.mindfree.todo

sealed interface TodoEvent {
    object SaveTodo: TodoEvent
    data class SetTodoText(val todoText: String): TodoEvent
    data class DeleteTodo(val todo: Todo): TodoEvent
}