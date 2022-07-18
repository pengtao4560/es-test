package starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动类
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringSourceCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSourceCodeApplication.class, args);
    }
}
