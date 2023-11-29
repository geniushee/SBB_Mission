package com.example.demo;

import com.example.demo.sbb.DemoApplication;
import com.example.demo.sbb.answer.Answer;
import com.example.demo.sbb.answer.AnswerService;
import com.example.demo.sbb.question.Question;
import com.example.demo.sbb.question.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DemoApplication.class)
@ActiveProfiles("test")
public class H2DBtestApplication {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @Test
    @DisplayName("test start")
    void t1() {

    }

    @Test
    @DisplayName("테스트용 기초데이터 생성확인")
    @Transactional
    void t2() {
        List<Question> questionList = questionService.findAll();

        assertEquals(questionList.get(0).getSubject(), "제목1");
        Question question = questionList.get(9);
        String subject = question.getSubject();
        String content = question.getContent();
        String answers = question.getAnswerList().stream()
                .map(Answer::getContent)
                .collect(Collectors.joining("/"));
        System.out.println(String.format("1. %s\n2. %s\n3. %s", subject, content, answers));
//        assertThat(questionList.get(9).getAnswerList())
//                .hasSize(3);

    }
}
