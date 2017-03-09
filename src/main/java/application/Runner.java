package application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@SpringBootApplication
public class Runner extends SpringBootServletInitializer{
    public static void main(String a[]) throws IOException {
        prepareSystem(a);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Runner.class);
    }

    private static void prepareSystem(String args[]) throws IOException {
        Properties loadProps = new Properties();
        if(System.getProperties().containsKey("lardi.conf")){
            loadProps.load(new InputStreamReader(new FileInputStream(System.getProperty("lardi.conf"))));
        }
        else {
            loadProps.load(new InputStreamReader(new FileInputStream("src\\main\\resources\\propu/mysql.properties")));
        }

        SpringApplication application = new SpringApplication(Runner.class);
        application.setDefaultProperties(loadProps);
        application.run(args);
    }
}