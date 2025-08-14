package com.talentprogram.batch_8.thymeleafapp.controller;

import com.talentprogram.batch_8.thymeleafapp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String helloPage(Model model){
        model.addAttribute("name", "Khine Phoo Wai");
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("items", List.of("Apple", "Banana", "Cherry"));
        return "hello";
    }

    @GetMapping("/greeting")
    public String greetingPage(Model model){
        String greeting;
        int hour = LocalTime.now().getHour();
        if (hour < 12) {
            greeting = "Hello, Good Morning!";
        } else if (hour < 18) {
            greeting = "Hello, Good Afternoon!";
        } else {
            greeting = "Hello, Good Evening!";
        }

        model.addAttribute("greeting", greeting);

        return "greeting";
    }

    @GetMapping("/task")
    public String taskPage(Model model){
        List<Task> tasks = List.of(
                new Task(1, "To do homework", "In Progress"),
                new Task(2, "To clean my room", "Undone"),
                new Task(3, "To sleep", "Undone")
        );

        model.addAttribute("task",tasks);
        return "task";
    }
}
