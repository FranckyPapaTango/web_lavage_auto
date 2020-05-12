package mgw.web_lavage_auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 *
 * @author BigWave
 */

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"mgw"})
@EntityScan(basePackages = {"mgw"})
@ComponentScan(basePackages = {"mgw"})
@PropertySource({"classpath:application.properties"})
@Configuration
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
        
        //ApplicationContext ctx=SpringApplication.run(DemoApplication.class, args);
   


}
