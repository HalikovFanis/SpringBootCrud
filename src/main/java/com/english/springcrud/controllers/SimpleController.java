package com.english.springcrud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/simple")
public class SimpleController {

    @GetMapping("pastSimple/pastSimple")
    public String pastSimple() {
        return "simple/pastSimple/pastSimple";
    }

    @GetMapping("pastSimple/pastSimpleAffirmative")
    public String pastSimpleAffirmative() {
        return "simple/pastSimple/pastSimpleAffirmative";
    }

    @GetMapping("pastSimple/pastSimpleNegative")
    public String pastSimpleNegative() {
        return "simple/pastSimple/pastSimpleNegative";
    }

    @GetMapping("pastSimple/pastSimpleQuestions")
    public String pastSimpleQuestions() {
        return "simple/pastSimple/pastSimpleQuestions";
    }
}
