package com.jmelon.onlinecourse;

import com.jmelon.onlinecourse.Util.SimpleStorageManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OnlineCourseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OnlineCourseApplication.class);
        init(context);
    }

    static void init(ConfigurableApplicationContext context) {
        SimpleStorageManager stm = context.getBean(SimpleStorageManager.class);
        stm.init();
    }
}