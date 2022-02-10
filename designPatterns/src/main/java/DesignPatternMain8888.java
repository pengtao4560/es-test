import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther zzyy
 * @create 2020-02-23 14:44
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DesignPatternMain8888 {

    public static void main(String[] args)
    {
        SpringApplication.run(DesignPatternMain8888.class,args);
    }
}
