package com.mysite.sbb.controller;

import com.mysite.sbb.domain.AnswerForm;
import com.mysite.sbb.domain.Question;
import com.mysite.sbb.domain.QuestionForm;
import com.mysite.sbb.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/list")
    public String showQuestions(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
        Page<Question> paging =  this.questionService.getList(page);
        model.addAttribute("paging", paging);
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
