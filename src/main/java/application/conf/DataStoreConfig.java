package application.conf;
import application.data.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataStoreConfig{
    @Autowired
    ApplicationContext context;

    @Bean()
    public UserService getUserService(){
        if(context.getEnvironment().getProperty("spring.datastoretype").equals("json")) return new JSONUserService();
        else if(context.getEnvironment().getProperty("spring.datastoretype").equals("mysql")) return new MySQLUserService();
        return null;
    }

    @Bean()
    public RecordService getRecordService(){
        if(context.getEnvironment().getProperty("spring.datastoretype").equals("mysql")) return new MySQLRecordService();
        else if (context.getEnvironment().getProperty("spring.datastoretype").equals("json")) return new JSONRecordService();
        return null;
    }
}