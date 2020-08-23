package demo.web.restcontroller;

import demo.web.model.Answer;
import demo.web.model.NumberInput;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class NumberInputController {

    @PostMapping(value = "/save-number-input", consumes = "application/json")
    public ResponseEntity<Resource> saveNumberInput(@RequestBody NumberInput numberInput) throws IOException {

        final String fileName = String.format("storage\\%s.txt", numberInput.getTaskName());
        final PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
        writer.println(numberInput.getTaskName());
        writer.println(numberInput.getInput());
        writer.close();

        final File file = new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(file.length())
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
    }

    @PostMapping(value = "/jquery-save-number-input", consumes = "application/json")
    public ResponseEntity<Answer> jquerySaveNumberInput(@RequestBody NumberInput numberInput) throws IOException {

        return new ResponseEntity<>(
                new Answer("taskName=" + numberInput.getTaskName() + "&input=" + numberInput.getInput()),
                HttpStatus.OK);
    }

    @GetMapping(value = "/jquery-download-number-input")
    public ResponseEntity<Resource> jqueryCreateAndDownloadNumberInput(@RequestParam String taskName, @RequestParam int input) throws IOException {

        final String fileName = String.format("storage\\%s.txt", taskName);
        final PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
        writer.println(taskName);
        writer.println(input);
        writer.close();

        final File file = new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(file.length())
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
    }

    @PostMapping("/upload-number-input")
    public ResponseEntity<NumberInput> uploadNumberInput(@RequestParam("file") MultipartFile file) {

        final NumberInput badNumberInput = new NumberInput("", 0);

        if (file.isEmpty()) {
            return new ResponseEntity<>(badNumberInput, HttpStatus.NO_CONTENT);
        } else {
            try {

                String[] lines = new String(file.getBytes(), StandardCharsets.UTF_8).split(System.lineSeparator());
                return new ResponseEntity<>(new NumberInput(lines[0], Integer.parseInt(lines[1])), HttpStatus.OK);

            } catch (NumberFormatException exc) {
                return new ResponseEntity<>(badNumberInput, HttpStatus.BAD_REQUEST);
            } catch (IOException exc) {
                return new ResponseEntity<>(badNumberInput, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
