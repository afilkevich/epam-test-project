package com.epam.project.serviceimpl;

import com.epam.project.model.Depo;
import com.epam.project.service.DepoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by master on 14.3.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-depo-service-test.xml"})
@Transactional
public class DepoServiceImplTest {
   
    private static final Logger LOGGER= LogManager.getLogger();

    @Autowired
    DepoService depoService;

    @Test
    public void getAllDepo() throws Exception {
        LOGGER.debug("test: getallDepo");
        List<Depo> depos=depoService.getAllDepo();
        Assert.assertEquals(2,depos.size());

    }

    @Test
    public void getDepoById() throws Exception {

    }

    @Test
    public void addDepo() throws Exception {

    }

    @Test
    public void updateDepo() throws Exception {

    }

    @Test
    public void deleteDepo() throws Exception {

    }

}