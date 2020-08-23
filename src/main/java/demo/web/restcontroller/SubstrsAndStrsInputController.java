package demo.web.restcontroller;

import demo.web.model.Answer;
import demo.web.model.SubstrsAndStrs;
import demo.web.model.SubstrsAndStrsInput;
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
public class SubstrsAndStrsInputController {

    @PostMapping(value = "/save-substrs-and-strs-input", consumes = "application/json")
    public ResponseEntity<Resource> saveSubstrsAndStrsInput(@RequestBody SubstrsAndStrsInput substrsAndStrsInput) throws IOException {

        if (substrsAndStrsInput.getSubstrings().length > 10 || substrsAndStrsInput.getStrings().length > 10) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        final String fileName = String.format("storage\\%s.txt", substrsAndStrsInput.getTaskName());
        final PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
        writer.println(substrsAndStrsInput.getTaskName());
        writer.println(String.join(" ", substrsAndStrsInput.getSubstrings()));
        writer.println(String.join(" ", substrsAndStrsInput.getStrings()));
        writer.close();

        final File file = new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(file.length())
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
    }

    @PostMapping(value = "/jquery-save-substrs-and-strs-input", consumes = "application/json")
    public ResponseEntity<Answer> jquerySubstrsAndStrsInput(@RequestBody SubstrsAndStrsInput substrsAndStrsInput) throws IOException {

        if (substrsAndStrsInput.getSubstrings().length > 10 || substrsAndStrsInput.getStrings().length > 10) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        final String response = "taskName=" + substrsAndStrsInput.getTaskName() +
                "&substrs=" + String.join(" ", substrsAndStrsInput.getSubstrings()) +
                "&strs=" + String.join(" ", substrsAndStrsInput.getStrings());

        return new ResponseEntity<>(new Answer(response), HttpStatus.OK);
    }

    @GetMapping(value = "/jquery-download-substrs-and-strs-input")
    public ResponseEntity<Resource> jqueryCreateAndDownloadSubstrsAndStrsInput
            (@RequestParam String taskName, @RequestParam String substrs, @RequestParam String strs)
            throws IOException {

        final String fileName = String.format("storage\\%s.txt", taskName);
        final PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
        writer.println(taskName);
        writer.println(substrs);
        writer.println(strs);
        writer.close();

        final File file = new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(file.length())
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
    }

    @PostMapping("/upload-substrs-and-strs-input")
    public ResponseEntity<SubstrsAndStrs> uploadSubstrsAndStrsInput(@RequestParam("file") MultipartFile file) {

        final SubstrsAndStrs badInput = new SubstrsAndStrs(null, null);

        if (file.isEmpty()) {
            return new ResponseEntity<>(badInput, HttpStatus.NO_CONTENT);
        } else {
            try {

                String[] lines = new String(file.getBytes(), StandardCharsets.UTF_8).split(System.lineSeparator());
                return new ResponseEntity<>(new SubstrsAndStrs(lines[1].split(" "), lines[2].split(" ")), HttpStatus.OK);

            } catch (NumberFormatException exc) {
                return new ResponseEntity<>(badInput, HttpStatus.BAD_REQUEST);
            } catch (IOException exc) {
                return new ResponseEntity<>(badInput, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
