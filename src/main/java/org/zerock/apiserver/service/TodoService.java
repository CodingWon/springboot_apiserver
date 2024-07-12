package org.zerock.apiserver.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.TodoDTO;

@Transactional
public interface TodoService {

    TodoDTO get(Long tno);


    // Entity -> DTO
    default TodoDTO entitiyToDTO(Todo todo){
        return TodoDTO.builder()
                            .tno(todo.getTno())
                            .title(todo.getTitle())
                            .content(todo.getContent())
                            .complete(todo.isComplete())
                            .dueDate(todo.getDueDate()).
                            build();
    }
    // DTO -> Entity
    default Todo dtoToentitiy(TodoDTO todoDto){
        return Todo.builder()
                .tno(todoDto.getTno())
                .title(todoDto.getTitle())
                .content(todoDto.getContent())
                .complete(todoDto.isComplete())
                .dueDate(todoDto.getDueDate()).
                build();
    }

}
