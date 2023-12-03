package com.example.demo.sbb.answer;

import com.example.demo.sbb.DataNotFoundException;
import com.example.demo.sbb.question.Question;
import com.example.demo.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer create(Question question,String content,
                       SiteUser author){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }

    public Answer getAnswer(Integer id){
        Optional<Answer> opAnswer = answerRepository.findById(id);
        if(opAnswer.isPresent()){
            return opAnswer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content){
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        answerRepository.save(answer);
    }

    public void delete(Answer answer){
        answerRepository.delete(answer);
    }

    public void vote(Answer answer, SiteUser siteUser){
        answer.getVoter().add(siteUser);
        answerRepository.save(answer);
    }

    public <Answer> Page<Answer> paging(List<Answer> answerList, int page){
        final int size = 2; // 한 페이지에 들어가는 content 수

        int start = Math.toIntExact(size * (long) page); // 페이지의 시작 컨텐츠 번호
        int end = Math.min(start + size, answerList.size()); // 페이지의 마지막 컨텐츠 번호
        List<Answer> sublist = answerList.subList(start, end); // 리스트의 일부 = 한 페이지

        List<Sort.Order> sorts = new ArrayList<>(); // 정렬 조건 List
        sorts.add(Sort.Order.desc("voter")); // 추천순으로 내림차순
//        sorts.add(Sort.Order.desc("CreateDate")); // 만든 날짜 내림차순
        Pageable pageable = PageRequest.of(page, size, Sort.by(sorts)); // 페이지 요청

        return new PageImpl<>(sublist, pageable, answerList.size());
    }
}
