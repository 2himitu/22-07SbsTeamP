package com.mysite.sbb.service;

import com.mysite.sbb.dao.QuestionRepository;
import com.mysite.sbb.domain.Question;
import com.mysite.sbb.util.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;


    public List<Question> getList(){
        return questionRepository.findAll();
    }
    public Question getQuestion(Integer id) {
    Optional<Question> opQuestion = this.questionRepository.findById(id);
        if (opQuestion.isPresent()) {
        Question question = opQuestion.get();
        question.setViewCount(question.getViewCount() + 1);
        this.questionRepository.save(question);
        return question;
        }else{
            throw new DataNotFoundException("question not found");
        }
    }
    public void create(String subject ,String content){
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());

        this.questionRepository.save(question);

    }

    public void setViewCountWhenLikeAndCreateAnswer(Integer id) {
        Question question = this.questionRepository.findById(id).get();
        Integer viewCount = question.getViewCount()-1;
        question.setViewCount(viewCount);
        questionRepository.save(question);
    }

    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }
}
