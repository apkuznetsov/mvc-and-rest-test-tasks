package demo.web.restcontroller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/save-task-input")
    public ResponseEntity<Resource> saveTaskInput() throws IOException {

        final String fileName = "storage" + "//file.txt";
        final PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
        writer.println("The first line");
        writer.println("The second line");
        writer.close();

        File file = new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
