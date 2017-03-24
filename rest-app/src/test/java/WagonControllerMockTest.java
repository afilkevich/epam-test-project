
import com.epam.project.model.Wagon;
import com.epam.project.rest.WagonRestController;
import com.epam.project.service.WagonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by master on 24.3.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value ={"classpath*:rest-spring-mock-test.xml"} )
public class WagonControllerMockTest {

    private static final Logger LOGGER= LogManager.getLogger();

    @Resource
    private WagonRestController wagonController;

    private MockMvc mockMvc;

    @Autowired
    private WagonService wagonService;

    private  Wagon wagon=new Wagon(1,"type",3,40,LocalDate.parse("2009-01-09"));

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
        LOGGER.debug("test:rest:getAllWagon");

        expect(wagonService.getAllWagon()).andReturn(Arrays.asList(new Wagon(1,"p",1,23,LocalDate.parse("2009-01-09"))));
        replay(wagonService);

        mockMvc.perform(
                get("/wagon/getAllWagon")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getWagonByIdTest() throws Exception{
        LOGGER.debug("test:rest:getWagonById");
         expect(wagonService.getWagonById(wagon.getId())).andReturn(wagon);
        replay(wagonService);

        mockMvc.perform(
                get("/wagon/get/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllWagonByDepo() throws Exception{
        LOGGER.debug("test:rest:getWagonByDepo");
        expect(wagonService.getAllWagonByDepo(wagon.getDepoId())).andReturn(Arrays.asList(wagon));
        replay(wagonService);

        mockMvc.perform(
                get("/wagon/getByDepo/3")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void addWagonTest() throws Exception{
        LOGGER.debug("test:rest:addWagon");
        expect(wagonService.addWagon(anyObject(Wagon.class))).andReturn(0);
        replay(wagonService);

        String wagon=new ObjectMapper().writeValueAsString(new Wagon());

        mockMvc.perform(
                post("/wagon/add")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(wagon))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("0"));


    }

}
