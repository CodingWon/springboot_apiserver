package org.zerock.apiserver.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@ToString
@Getter             // 이뮤터블하게 하기 위해 getter 만
@Builder            //Build , AAC, NAC 세트로 지정
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_todo")   // 생성되는 테이블의 이름을 지정
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;
    @Column(length = 500, nullable = false) // NOT NULL
    private String title;
    private String content;
    private boolean complete;
    private LocalDate dueDate;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeComplete(boolean complete) {
        this.complete = complete;
    }

    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
