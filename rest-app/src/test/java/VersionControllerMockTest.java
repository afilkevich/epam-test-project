import com.epam.project.rest.VersionController;
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

import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by master on 26.3.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath*:rest-spring-mock-test.xml"})
public class VersionControllerMockTest {
    @Resource
    private VersionController versionController;

    private MockMvc mockMvc;



    @Before
    public void setUp(){
        mockMvc=standaloneSetup(versionController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

   @Test
    public void getVersionTest() throws Exception{
       mockMvc.perform(
               get("/version")
               .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string("\"version-1.0\""));
   }

}
