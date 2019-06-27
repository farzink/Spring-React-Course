package com.jmelon.onlinecourse;

import com.jmelon.onlinecourse.Util.SimpleStorageManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineCourseApplication {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        //System.out.println(new App().getGreeting());

        var context = SpringApplication.run(OnlineCourseApplication.class);
        SimpleStorageManager stm = context.getBean(SimpleStorageManager.class);
        stm.init();
    }
}