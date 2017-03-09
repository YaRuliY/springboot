package controller;
import application.Runner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Runner.class)
@WebAppConfiguration
public class TestUserController{
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @BeforeClass
    public static void setupClass() throws IOException {
        Properties loadProps = new Properties();
        if(System.getProperties().containsKey("lardi.conf")){
            loadProps.load(new InputStreamReader(new FileInputStream(System.getProperty("lardi.conf"))));
        }
        else {
            loadProps.load(new InputStreamReader(new FileInputStream("src\\main\\resources\\propu/mysql.properties")));
        }

        System.getProperties().putAll(loadProps);
    }

    @Before
    public void setUp(){
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testGetUsers() throws Exception{
        this.mvc.perform(get("/user/all")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testAddUser() throws Exception{
        this.mvc.perform(post("/user/add")
                .param("username", "gerasim")
                .param("password", "12345")
                .param("confirm", "12345")
                .param("fio", "Ivanov GP")).andExpect(status().isOk())
                .andExpect(content().string("Hell Yeah!<br>You are new user!"));
    }
}