package demo.web.restcontroller;

import demo.logic.solver.Solver;
import demo.logic.solver.ZeroOrNegativeNumberException;
import demo.web.model.Answer;
import demo.web.model.Number;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SolverController {

    @PostMapping(value = "/get-expanded-form", consumes = "application/json")
    public ResponseEntity<Answer> getExpandedForm(@RequestBody Number number) {

        String answer;
        try {
            answer = Solver.getExpandedForm(number.getNumber());
        } catch (NullPointerException | NumberFormatException exc) {
            answer = "ОШИБКА. Введите число";
        } catch (ZeroOrNegativeNumberException exc) {
            answer = "ОШИБКА. Введит целое число больше нуля";
        } catch (IllegalArgumentException exc) {
            answer = "ОШИБКА. Слишком большое число";
        }

        return new ResponseEntity<>(new Answer(answer), HttpStatus.OK);
    }
}
