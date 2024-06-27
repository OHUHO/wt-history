package com.history.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "HelloController")
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello  中国历史平台";
    }
}
