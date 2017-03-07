package application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class Runner extends SpringBootServletInitializer implements CommandLineRunner{
    public static void main(String a[]){ SpringApplication.run(Runner.class, a); }

    @Override
    public void run(String... strings) throws Exception {}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Runner.class);
    }
}