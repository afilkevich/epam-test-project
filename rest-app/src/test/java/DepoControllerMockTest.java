import com.epam.project.model.Depo;
import com.epam.project.rest.DepoRestController;
import com.epam.project.service.DepoService;
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
@ContextConfiguration(locations = {"classpath*:rest-spring-mock-test.xml"})
public class DepoControllerMockTest {

    @Resource
    private DepoRestController depoController;

    private MockMvc mockMvc;

    @Autowired
    private DepoService depoService;

    @Before
    public void setUp(){
        mockMvc=standaloneSetup(depoController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown(){
        verify(depoService);
        reset(depoService);
    }

    @Test
    public void getAllDepo() throws Exception{
    expect(depoService.getAllDepo()).andReturn(Arrays.<Depo>asList(new Depo(1,"p")));
        replay(depoService);

        mockMvc.perform(
                get("/depo/getAll")
                .accept(MediaType.APPLICATION_JSON)
                 ).andDo(print())
                .andExpect(status().isOk());
    }











}
