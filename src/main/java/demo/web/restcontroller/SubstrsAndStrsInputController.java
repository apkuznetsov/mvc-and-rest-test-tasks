package demo.web.restcontroller;

import demo.web.model.SubstrsAndStrsInput;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
