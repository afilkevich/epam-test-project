
import com.epam.project.model.Wagon;
import com.epam.project.rest.WagonRestController;
import com.epam.project.service.WagonService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by master on 24.3.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value ={"classpath*:rest-spring-mock-test.xml"} )
public class WagonControllerMockTest {

    @Resource
    private WagonRestController wagonController;

    private MockMvc mockMvc;

    @Autowired
    private WagonService wagonService;

    @Before
    public void setUp(){
        mockMvc=standaloneSetup(wagonController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown(){
        verify(wagonService);
        reset(wagonService);
    }

    @Test
    public void getAllWagonTest() throws Exception{
        expect(wagonService.getAllWagon()).andReturn(Arrays.<Wagon>asList(new Wagon(1,"p",1,23,LocalDate.parse("2009-01-09"))));
        replay(wagonService);

        mockMvc.perform(
                get("/wagon/getAllWagon")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }
}
