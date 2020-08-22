package demo.web.restcontroller;

import demo.web.model.TaskInput;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
public class SaverController {

    @PostMapping(value = "/save-task-input", consumes = "application/json")
    public ResponseEntity<Resource> saveTaskInput(@RequestBody TaskInput taskInput) throws IOException {

        final String fileName = String.format("storage\\%s.txt", taskInput.getTaskName());
        final PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
        writer.println(taskInput.getTaskName());
        writer.println(taskInput.getInput());
        writer.close();

        final File file = new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
