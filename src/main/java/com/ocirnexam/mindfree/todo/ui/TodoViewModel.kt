package com.ocirnexam.mindfree.todo.ui

import androidx.lifecycle.ViewModel
import com.ocirnexam.mindfree.todo.entities.Todo
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

            is TodoEvent.SetTodoDescription -> {
                _state.update {
                    it.copy(
                        todoDescription = event.todoDescription
                    )
                }
            }

            is TodoEvent.DeleteTodo -> {
                _todos.remove(event.todo)
            }

            TodoEvent.SaveTodo -> {
                if (_state.value.todoText.isBlank() || _state.value.todoDescription.isBlank())
                    return
                _todos.add(Todo(_state.value.todoText, _state.value.todoDescription, false))
                _state.update {
                    it.copy(
                        todoText = "",
                        todoDescription = ""
                    )
                }
            }
        }
    }
}