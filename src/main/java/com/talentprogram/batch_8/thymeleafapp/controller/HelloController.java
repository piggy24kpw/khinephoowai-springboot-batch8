package com.talentprogram.batch_8.thymeleafapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    private int id;
    private String description;
    private String status;

    public HelloController(int id, String description, String status) {
        this.status = status;
        this.description = description;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


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
        List<HelloController> tasks = new ArrayList<>();
        tasks.add(new HelloController(1,"To do homework", "progress"));
        tasks.add(new HelloController(2,"To clean my room", "undone"));
        tasks.add(new HelloController(3, "To sleep", "undone"));

        model.addAttribute("task",tasks);
        return "task";
    }
}
