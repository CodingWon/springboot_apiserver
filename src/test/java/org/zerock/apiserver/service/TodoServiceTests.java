package org.zerock.apiserver.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.TodoDTO;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    TodoService todoService;

    @Test
    public void testGet(){
        Long tno = 50L;

        log.info(todoService.get(tno));
    }

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("Title")
                .content("Content...")
                .dueDate(LocalDate.of(2024,07,12))
                .build();

        log.info(todoService.register(todoDTO));
    }

    @Test
    public void testModify(){
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(1L)
                .title("Title_@@@@@@@@@@@@@@")
                .content("Content...@@@@@@@@@@@@@@")
                .dueDate(LocalDate.of(2024,07,12))
                .build();

        todoService.modify(todoDTO);
    }

    @Test
    public void testRemove(){
        todoService.remove(1L);
    }
}
