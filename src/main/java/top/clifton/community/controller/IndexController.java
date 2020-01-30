package top.clifton.community.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Clifton
 * @create 2020/1/30 - 14:46
 */
public class IndexController {

    @GetMapping("/")
    public String toIndex(){
        return "index";
    }

}
