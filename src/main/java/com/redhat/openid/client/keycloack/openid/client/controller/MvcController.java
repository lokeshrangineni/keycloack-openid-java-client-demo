package com.redhat.openid.client.keycloack.openid.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcController {

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/")
    public String redirecToHome(){
        return "home";
    }
}
