package top.clifton.community.advice;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.clifton.community.exception.QuestionNotFoundException;

/**
 * @author Clifton
 * @create 2020/2/8 - 15:40
 */
//@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        if (e instanceof QuestionNotFoundException){
            mv.addObject("message",e.getMessage());
        }else {
            mv.addObject("message","服务器异常，请稍后重试！");
        }
        e.printStackTrace();
        return mv;
    }

}
