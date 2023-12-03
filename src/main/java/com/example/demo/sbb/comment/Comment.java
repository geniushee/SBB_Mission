package com.example.demo.sbb.comment;

import com.example.demo.sbb.answer.Answer;
import com.example.demo.sbb.question.Question;
import com.example.demo.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser author;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime modifyDate;

    @ManyToOne
    private Answer answer;

    @ManyToOne
    private Question question;
}
