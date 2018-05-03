package com.example.springboot.restfulapiwithaoplogging;

import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @ControllerLog
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Object index(String name){
        if(StringUtils.isEmpty(name)){
            name = "world";
        }
        String[] sl = new String[]{"hello", name};
        return sl;
    }
}
