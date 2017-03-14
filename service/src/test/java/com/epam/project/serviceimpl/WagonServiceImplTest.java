package com.epam.project.serviceimpl;

import com.epam.project.model.Wagon;
import com.epam.project.service.WagonService;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.scope.ScopedProxyFactoryBean;
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
@ContextConfiguration(locations = {"classpath:spring-wagon-service-test.xml"})
@Transactional
public class WagonServiceImplTest {

    private static final Logger LOGGER= LogManager.getLogger();

    @Autowired
    private WagonService wagonService;


    @Test
    public void getAllWagonByDepo() throws Exception {
        LOGGER.debug("test:getAllWagonByDepo");
         List<Wagon>wagons= wagonService.getAllWagonByDepo(1);
        Assert.assertEquals(2,wagons.size());


    }

    @Test
    public void addWagon() throws Exception {

    }

    @Test
    public void getWagonById() throws Exception {

    }

    @Test
    public void updateWagon() throws Exception {

    }

    @Test
    public void deleteWagon() throws Exception {

    }

    @Test
    public void countWagonByDepo() throws Exception {

    }

    @Test
    public void sumOfSeatsByDepo() throws Exception {

    }

    @Test
    public void getWagonByDate() throws Exception {

    }

}