package com.ocirnexam.mindfree.todo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TodoViewModel() : ViewModel() {
    private var _todos: MutableList<Todo> = mutableListOf()
    private val _state = MutableStateFlow(TodoState(todos = _todos))
    val state = _state.asStateFlow()

    fun onEvent(event: TodoEvent) {
        when (event) {
            is TodoEvent.SetTodoText -> {
                _state.update {
                    it.copy(
                        todoText = event.todoText
                    )
                }
            }
            is TodoEvent.DeleteTodo -> {
                _todos.remove(event.todo)
            }

            TodoEvent.SaveTodo -> {
                if (_state.value.todoText.isBlank())
                    return
                _todos.add(0, Todo(_state.value.todoText, false))
                _state.update {
                    it.copy(
                        todoText = ""
                    )
                }
            }
        }
    }
}