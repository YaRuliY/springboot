package controller;
import application.Runner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Runner.class)
@WebAppConfiguration
public class TestRecordController {
    @Autowired private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp(){ this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build(); }

    @Test
    public void testHome() throws Exception {
        this.mvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string("Hello!"));
    }

    @Test
    public void testGetRecords() throws Exception {
        this.mvc.perform(get("/get/records")).andExpect(status().isOk());
                /*.andExpect(content().string("Hello!"));*/
    }
}