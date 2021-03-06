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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Runner.class)
@WebAppConfiguration
public class TestRecordController {
    @Autowired private WebApplicationContext context;
    private MockMvc mvc;

    @BeforeClass
    public static void setupClass() throws IOException {
        Properties loadProps = new Properties();
        if(System.getProperties().containsKey("lardi.conf")){
            loadProps.load(new InputStreamReader(new FileInputStream(System.getProperty("lardi.conf"))));
        }
        else {
            loadProps.load(new InputStreamReader(new FileInputStream("src\\main\\resources\\propu/json.properties")));
        }

        System.getProperties().putAll(loadProps);
    }

    @Before
    public void setUp(){ this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build(); }

    @Test
    public void testHome() throws Exception{
        this.mvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string("Hello!"));
    }

    @Test
    public void testGetRecords() throws Exception{
        this.mvc.perform(get("/get/records").with(request -> {
            request.setRemoteUser("root");
            return request;
        }))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testSearchRecords() throws Exception{
        this.mvc.perform(get("/search/9").with(request -> {
            request.setRemoteUser("root");
            return request;
        }))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(content()
                .string("[{\"id\":0,\"name\":\"newName\",\"surname\":\"newSurName\",\"patronymic\":\"newPatronymic\",\"" +
                        "telephone\":\"380977772233\",\"hometel\":\"\",\"address\":\"\",\"email\":\"\",\"user_id\":1}]"));
    }

    @Test
    public void testUpdateRecord() throws Exception {
        this.mvc.perform(put("/edit/record/0").with(request -> {
            request.setRemoteUser("root");
            request.setParameter("name","newName");
            request.setParameter("surname","newSurName");
            request.setParameter("patronymic","newPatronymic");
            request.setParameter("telephone","380977772233");
            request.setParameter("hometel","");
            request.setParameter("address","");
            request.setParameter("email","");
            return request;
        }))
                .andExpect(status().isOk())
                .andExpect(content().string("Record is edited"));
    }
}