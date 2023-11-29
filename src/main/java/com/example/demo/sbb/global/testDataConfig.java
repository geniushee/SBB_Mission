package com.example.demo.sbb.global;


import com.example.demo.sbb.answer.AnswerService;
import com.example.demo.sbb.question.Question;
import com.example.demo.sbb.question.QuestionService;
import com.example.demo.sbb.user.SiteUser;
import com.example.demo.sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Profile("test")
@Configuration
@RequiredArgsConstructor
public class testDataConfig {

    @Autowired
    @Lazy
    private testDataConfig self;

    private final UserService userService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Bean
    public ApplicationRunner init() {
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1() {
        if (userService.count() > 0) return;

        for (int i = 1; i < 11; i++) {
            String user = "user" + i;
            String password = "1234";
            String userEmail = String.format("aa%d@aa%d.com", i, i);
            SiteUser siteuser = userService.create(user, password, userEmail);
            String subject = "제목" + i;
            String content = "내용" + i;
            questionService.create(subject, content, siteuser);
        }

        List<Question> questionList = questionService.findAll();
        for (int j = 0 ; j < questionList.size(); j++){
            Question question = questionService.getQuestion(j + 1);
            for (int i = 1; i < 4; i++){
                String content = "답글" + i;
                SiteUser siteUser = userService.getUser(String.format("user%d", i));
                answerService.create(question, content, siteUser);
            }
        }
        questionList = questionService.findAll();

    }
}
