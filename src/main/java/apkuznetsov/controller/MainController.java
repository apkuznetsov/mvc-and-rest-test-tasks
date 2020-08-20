package apkuznetsov.controller;

import apkuznetsov.solver.Solver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(value = "/",
            method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        return modelAndView;
    }

    @RequestMapping(value = "/get-expanded-form",
            method = RequestMethod.POST)
    public String getExpandedForm(String number) {
        int numberAsInt = Integer.parseInt(number);

        return Solver.getExpandedForm(numberAsInt);
    }
}
