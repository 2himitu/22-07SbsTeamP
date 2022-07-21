package com.mysite.sbb.controller;

import com.mysite.sbb.domain.AnswerForm;
import com.mysite.sbb.domain.Question;
import com.mysite.sbb.domain.QuestionForm;
import com.mysite.sbb.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/list")
    public String showQuestions(Model model) {
        List<Question> questionList = questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @RequestMapping("/detail/{id}")
    public String showQuestions(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);

        return "question_detail";
    }
    @RequestMapping("/create")
    public String showQuestionsCreateFrom(QuestionForm questionForm) {

        return "question_form";
    }

    @PostMapping("/create")
    public String showQuestionsCreateFrom(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        // 질문만들기

        return "redirect:/question/list";
    }


}
