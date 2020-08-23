package demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApp {

    public static String TASK_1_NAME = "Поиск по подстрокам";
    public static String TASK_2_NAME = "Расширенная форма числа";

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }
}
