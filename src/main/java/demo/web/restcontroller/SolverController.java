package demo.web.restcontroller;

import demo.web.model.Answer;
import demo.web.model.Number;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static demo.logic.solver.Solver.getExpandedForm;

@RestController
@RequestMapping("/api")
public class SolverController {

    @PostMapping(value = "/get-expanded-form", consumes = "application/json")
    public ResponseEntity<Answer> getExpandedFormF(@RequestBody Number number) {

        return new ResponseEntity<>(new Answer(getExpandedForm(number.getNumber())), HttpStatus.OK);
    }
}
