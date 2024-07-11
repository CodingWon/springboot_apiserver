package org.zerock.apiserver.repository;



import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.apiserver.domain.Todo;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test1(){
        Assertions.assertNotNull(todoRepository);
        log.info(" @@@@@@@@@@@@ " + todoRepository.getClass().getName());
    }

    @Test
    public void testInsert(){
        final Todo todo = Todo.builder()
                .title("Title")
                .content("Content...")
                .dueDate(LocalDate.of(2023,12,30))
                .build();

        //save 시 return type entity
        Todo result = todoRepository.save(todo);
        log.info(result);
    }

    @Test
    public void testRead(){

        Long tno = 1L;
        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();   // 문제 없으면 값반환, 있으면 throw

        log.info(todo);

    }
}
