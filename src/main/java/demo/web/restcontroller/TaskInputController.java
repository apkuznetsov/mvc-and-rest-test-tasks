package demo.web.restcontroller;

import demo.web.model.Answer;
import demo.web.model.TaskInput;
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
public class TaskInputController {

    @PostMapping(value = "/save-task-input", consumes = "application/json")
    public ResponseEntity<Answer> saveTaskInput(@RequestBody TaskInput taskInput) throws IOException {

        return new ResponseEntity<>(
                new Answer("taskName=" + taskInput.getTaskName() + "&input=" + taskInput.getInput()),
                HttpStatus.OK);
    }

    @GetMapping(value = "/download-task-input")
    public ResponseEntity<Resource> createAndDownloadTaskInput(@RequestParam String taskName, @RequestParam int input) throws IOException {

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

    @PostMapping("/upload-task-input")
    public ResponseEntity<TaskInput> uploadTaskInput(@RequestParam("file") MultipartFile file) {

        final TaskInput badTaskInput = new TaskInput("", 0);

        if (file.isEmpty()) {
            return new ResponseEntity<>(badTaskInput, HttpStatus.NO_CONTENT);
        } else {
            try {

                String[] lines = new String(file.getBytes(), StandardCharsets.UTF_8).split(System.lineSeparator());
                return new ResponseEntity<>(new TaskInput(lines[0], Integer.parseInt(lines[1])), HttpStatus.OK);

            } catch (NumberFormatException exc) {
                return new ResponseEntity<>(badTaskInput, HttpStatus.BAD_REQUEST);
            } catch (IOException exc) {
                return new ResponseEntity<>(badTaskInput, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
