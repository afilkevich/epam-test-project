package com.epam.project.serviceimpl;

import com.epam.project.model.Depo;
import com.epam.project.model.DepoDTO;
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
    private static final Depo testDepo=new Depo("Mogilev wagon depo");

    @Autowired
    DepoService depoService;

    @Test
    public void getAllDepo() throws Exception {
        LOGGER.debug("test: getallDepo");
        List<DepoDTO> depos=depoService.getAllDepo();
        Assert.assertEquals(2,depos.size());

    }

    @Test
    public void getDepoById() throws Exception {
        LOGGER.debug("test:getDepoById");
        Depo depo=depoService.getDepoById(2);
        Assert.assertNotNull(depo);
        Assert.assertEquals((Integer) 2,depo.getId());

    }

    @Test
    public void addDepo() throws Exception {
        LOGGER.debug("test:addDepo");
        Integer id=depoService.addDepo(testDepo);
        Assert.assertNotNull(id);
        List<DepoDTO> depos=depoService.getAllDepo();
        Assert.assertEquals(3,depos.size());

    }

    @Test
    public void updateDepo() throws Exception {
        LOGGER.debug("test:updateDepo");
        Depo depo=depoService.getDepoById(1);
        depo.setName("Gomel wagon park");
        int line=depoService.updateDepo(depo);
        Assert.assertEquals(line,1);

    }

    @Test
    public void deleteDepo() throws Exception {
        LOGGER.debug("test:deleteDepo");
        List<DepoDTO> depos=depoService.getAllDepo();
        int oldSize=depos.size();
       int line= depoService.deleteDepo(1);
        Assert.assertEquals(1,line);
        depos=depoService.getAllDepo();
        Assert.assertEquals(oldSize-1,depos.size());

    }

}