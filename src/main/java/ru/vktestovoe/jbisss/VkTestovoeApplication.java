package ru.vktestovoe.jbisss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class VkTestovoeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VkTestovoeApplication.class, args);
    }
}
