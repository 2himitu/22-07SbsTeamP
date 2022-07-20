package com.mysite.sbb.service;


import com.mysite.sbb.dao.AnswerRepository;
import com.mysite.sbb.domain.Answer;
import com.mysite.sbb.domain.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setReplyLike(false);
        this.answerRepository.save(answer);
}

    public void setLike(Integer answerId) {
        Answer answer = answerRepository.findById(answerId).get();
        if (answer.getReplyLike() == true) {
            answer.setReplyLike(false);
        } else {
            answer.setReplyLike(true);
        }
    }
}