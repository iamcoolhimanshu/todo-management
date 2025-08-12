package com.SpBoot.todo_management.Service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.SpBoot.todo_management.Service.TodoService;
import com.SpBoot.todo_management.dto.TodoDto;
import com.SpBoot.todo_management.entity.Todo;
import com.SpBoot.todo_management.exception.ResourceNotFoundException;
import com.SpBoot.todo_management.repository.TodoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoImpl implements TodoService {

	private TodoRepository todoRepository;

	private ModelMapper modelMapper;

	@Override
	public TodoDto addTodo(TodoDto todoDto) {

		// CONVERT TODODTO INTO TODO JPA ENTITY
		Todo todo = modelMapper.map(todoDto, Todo.class);

		// TODO JPA ENTITY
		Todo savedTodo = todoRepository.save(todo);

		// CONVERT SAVED TODO JPA ENTITY OBJECT INTO TODODTA OBJECT
		TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());

		Todo updateTodo = todoRepository.save(todo);
		return modelMapper.map(updateTodo, TodoDto.class);
	}

	@Override
	public void deleteTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
		todoRepository.deleteById(id);

	}

	@Override
	public TodoDto completeTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
		todo.setCompleted(Boolean.TRUE);
		Todo updateTodo = todoRepository.save(todo);
		return modelMapper.map(updateTodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
		todo.setCompleted(Boolean.FALSE);
		Todo updateTodo = todoRepository.save(todo);
		return modelMapper.map(updateTodo, TodoDto.class);
	}

}
