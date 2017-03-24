import com.epam.project.model.Depo;
import com.epam.project.rest.DepoRestController;
import com.epam.project.service.DepoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.test.web.servlet.ResultMatcher;

import javax.annotation.Resource;

import java.util.Arrays;

import static org.easymock.EasyMock.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by master on 24.3.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:rest-spring-mock-test.xml"})
public class DepoControllerMockTest {

    private static final Logger LOGGER= LogManager.getLogger();

    @Resource
    private DepoRestController depoController;

    private MockMvc mockMvc;

    @Autowired
    private DepoService depoService;

    private Depo depo=new Depo(3,"moscow train");

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
    public void getAllDepoTest() throws Exception{
        LOGGER.debug("test:rest:getAllDepo");
    expect(depoService.getAllDepo()).andReturn(Arrays.<Depo>asList(new Depo(1,"p")));
        replay(depoService);

        mockMvc.perform(
                get("/depo/getAll")
                .accept(MediaType.APPLICATION_JSON)
                 ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getByIdTest()throws Exception {
        LOGGER.debug("test:rest:getByIdDepo");
        expect(depoService.getDepoById(depo.getId())).andReturn(depo);
        replay(depoService);

        mockMvc.perform(
                get("/depo/getById/3")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void addDepoTest() throws Exception{
        LOGGER.debug("test:rest:addDepo");
        expect(depoService.addDepo(anyObject(Depo.class))).andReturn(3);
        replay(depoService);

        String depos= new ObjectMapper().writeValueAsString(new Depo("edf"));

        mockMvc.perform(
                post("/depo/add")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(depos))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect( content().string("3"));
    }

    @Test
    public void updateDepoTest() throws Exception{
        LOGGER.debug("test:rest:updateDepo");
        expect(depoService.updateDepo(anyObject(Depo.class))).andReturn(0);
        replay(depoService);
        String depo=new ObjectMapper().writeValueAsString(new Depo(3,"gh"));

        mockMvc.perform(
                put("/depo/update")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(depo))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string("0"));
    }

    @Test
    public void deleteDepoTest() throws Exception{
        LOGGER.debug("test:rest:deleteDepo");
        expect(depoService.deleteDepo(anyObject(Integer.class))).andReturn(0);
        replay(depoService);
        mockMvc.perform(
                delete("/depo/delete/2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


    }












}
