package top.clifton.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Clifton
 * @create 2020/2/20 - 16:09
 */
@Controller
public class WebSocketController {

    @GetMapping("/websocket")
    public String toWebSocket(){
        return "websocket";
    }

}
