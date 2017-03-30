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

import java.time.LocalDate;
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

    private static final Wagon wagonTest=new Wagon(16245,"closed type",2,38, LocalDate.parse("2001-09-21"));
    private static  final LocalDate from=LocalDate.parse("2015-08-01");
    private static final LocalDate to=LocalDate.parse("2016-08-10");



    @Test
    public void getAllWagonByDepo() throws Exception {
        LOGGER.debug("test:getAllWagonByDepo");
         List<Wagon>wagons= wagonService.getAllWagonByDepo(1);
        Assert.assertEquals(2,wagons.size());


    }

    @Test
    public  void getAllWagon()throws Exception{
        LOGGER.debug("test:getAllWagon");
        List<Wagon> wagons=wagonService.getAllWagon();
        Assert.assertEquals(4,wagons.size());
    }

    @Test
    public void addWagon() throws Exception {
        LOGGER.debug("test:addWagon");
        List<Wagon> wagons=wagonService.getAllWagonByDepo(2);
        int oldsize=wagons.size();
        wagonService.addWagon(wagonTest);
        wagons=wagonService.getAllWagonByDepo(2);
        Assert.assertEquals(oldsize+1,wagons.size());
    }

    @Test
    public void getWagonById() throws Exception {
        LOGGER.debug("test:getWagonById");
        Wagon wagon=wagonService.getWagonById(14176);
        Assert.assertNotNull(wagon);
        Assert.assertEquals((Integer) 14176,wagon.getId());
    }

    @Test
    public void updateWagon() throws Exception {
        LOGGER.debug("test:updateWagon");
        Wagon wagon=wagonService.getWagonById(23245);
        wagon.setDepoId(1);
        int line=wagonService.updateWagon(wagon);
        Assert.assertEquals(1,line);
    }

    @Test
    public void deleteWagon() throws Exception {
        LOGGER.debug("test:deleteWagon");
        int line=wagonService.deleteWagon(23245);
        Assert.assertEquals(1,line);
    }


    @Test
    public void getWagonByDate() throws Exception {
        LOGGER.debug("test:getWagonByDate");
        List<Wagon> wagons=wagonService.getWagonByDate(from,to);
        Assert.assertEquals(2,wagons.size());

    }

}