package zw.co.afrosoft.myblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import zw.co.afrosoft.myblog.util.RoleCreator;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = {"zw.co.afrosoft.myblog"})
public class MyBlogApplication{

    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }

}
