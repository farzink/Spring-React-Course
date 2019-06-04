package com.jmelon.onlinecourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineCourseApplication {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        //System.out.println(new App().getGreeting());

        SpringApplication.run(OnlineCourseApplication.class);
    }
}