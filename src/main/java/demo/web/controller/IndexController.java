package demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "/task1", method = RequestMethod.GET)
    public String task1() {
        return "task1";
    }

    @RequestMapping(value = "/task2", method = RequestMethod.GET)
    public String task2() {
        return "task2";
    }
}
