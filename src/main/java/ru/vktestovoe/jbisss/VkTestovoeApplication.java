package ru.vktestovoe.jbisss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;
import ru.vktestovoe.jbisss.config.RestTemplateConfig;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        SecurityAutoConfiguration.class
})
@Import(RestTemplateConfig.class)
public class VkTestovoeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VkTestovoeApplication.class, args);
    }
}
