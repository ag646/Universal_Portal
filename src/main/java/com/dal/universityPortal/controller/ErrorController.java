package com.dal.universityPortal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.dal.universityPortal.constant.UrlConstant.ERROR;
import static com.dal.universityPortal.constant.UrlConstant.UNAUTHORIZED;

@Controller
@RequestMapping(ERROR)
public class ErrorController {

    @GetMapping(UNAUTHORIZED)
    public String unauthorized() {
        return "unauthorized";
    }
}
