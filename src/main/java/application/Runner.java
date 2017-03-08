package application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource("application.properties"),
        @PropertySource(value = "file:${Dlardi.conf}/application.properties", ignoreResourceNotFound = true)
})
public class Runner extends SpringBootServletInitializer{
    public static void main(String a[]){
        SpringApplication.run(Runner.class, a);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Runner.class);
    }
}