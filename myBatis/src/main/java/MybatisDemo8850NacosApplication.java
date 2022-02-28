import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MybatisDemo8850NacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemo8850NacosApplication.class, args);
    }

}
