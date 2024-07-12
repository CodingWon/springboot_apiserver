package org.zerock.apiserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.TodoDTO;
import org.zerock.apiserver.repository.TodoRepository;

import java.util.Optional;

//트랜잭션
@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    @Override
    public TodoDTO get(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        return entitiyToDTO(todo);
    }

    @Override
    public Long register(TodoDTO dto) {

        Todo todo = dtoToentitiy(dto);
        Todo result = todoRepository.save(todo); // 변경된 객체가 반환된다.

        return result.getTno();
    }

    @Override
    public void modify(TodoDTO dto) {
        Optional<Todo> findTodo = todoRepository.findById(dto.getTno());

        Todo todo = findTodo.orElseThrow();
        todo.changeTitle(dto.getTitle());
        todo.changeContent(dto.getContent());
        todo.changeComplete(dto.isComplete());
        todo.changeDueDate(dto.getDueDate());

        todoRepository.save(todo);
    }

    @Override
    public void remove(Long tno) {

        todoRepository.deleteById(tno);

    }
}
