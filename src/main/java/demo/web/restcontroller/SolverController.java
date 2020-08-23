package demo.web.restcontroller;

import demo.solver.ExpandedNumber;
import demo.solver.SubstringSearch;
import demo.solver.ZeroOrNegativeNumberException;
import demo.web.model.Answer;
import demo.web.model.Number;
import demo.web.model.SubstrsAndStrs;
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
            answer = ExpandedNumber.getExpandedForm(number.getNumber());
        } catch (NullPointerException | NumberFormatException exc) {
            answer = "ОШИБКА. Введите число";
        } catch (ZeroOrNegativeNumberException exc) {
            answer = "ОШИБКА. Введит целое число больше нуля";
        } catch (IllegalArgumentException exc) {
            answer = "ОШИБКА. Слишком большое число";
        }

        return new ResponseEntity<>(new Answer(answer), HttpStatus.OK);
    }

    @PostMapping(value = "/get-substrings-that-in-strings", consumes = "application/json")
    public ResponseEntity<String[]> getSubstringsThatInStrings(@RequestBody SubstrsAndStrs substrsAndStrs) {

        if (substrsAndStrs.getSubstrings().length > 10 || substrsAndStrs.getStrings().length > 10) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        final String[] result = SubstringSearch.getSubstringsThatInStrings(substrsAndStrs.getSubstrings(), substrsAndStrs.getStrings());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
