package apkuznetsov.restcontroller;

import apkuznetsov.model.RequestNumber;
import apkuznetsov.solver.Solver;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SolverController {

    @RequestMapping(value = "/get-expanded-form", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public @ResponseBody
    String getExpandedForm(@RequestBody RequestNumber number) {

        return Solver.getExpandedForm(number.getNumber());
    }
}
