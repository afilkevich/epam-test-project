package com.epam.project.daoimpl;

import com.epam.project.dao.WagonDao;
import com.epam.project.model.Wagon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by master on 11.3.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-wagon.xml"})
@Transactional
public class WagonDaoImplTest {
    private static final Logger LOGGER= LogManager.getLogger();

    private static final Wagon testWagon=new Wagon(23765,"opened type",1,52, LocalDate.parse("2001-09-10"));


    @Autowired
    WagonDao wagonDao;

    @Test
    public void getAllWagonByDepo() throws Exception {
        LOGGER.debug("test: getAllWagonByDepo");
        List<Wagon> wagons=wagonDao.getAllWagonByDepo(1);
        Assert.assertTrue(wagons.size()>0);

    }
    @Test
    public void getAllWagon() throws Exception{
        LOGGER.debug("test:getAllWagon");
        List<Wagon> wagons=wagonDao.getAllWagon();
        Assert.assertTrue(wagons.size()>0);
    }


    @Test
    public void addWagon()  throws Exception{
        LOGGER.debug("test:addWagon");
        List<Wagon>oldList=wagonDao.getAllWagonByDepo(1);
        wagonDao.addWagon(testWagon);
        List<Wagon>nList=wagonDao.getAllWagonByDepo(1);
        Assert.assertTrue(nList.size()==oldList.size()+1);
    }

    @Test
    public void updateWagon() throws Exception
    {
        LOGGER.debug("test:update wagon");
        Wagon wagon=wagonDao.getWagonById(23245);
        wagon.setDepoId(1);
        wagonDao.updateWagon(wagon);
        Wagon wagon1=wagonDao.getWagonById(23245);
        Assert.assertTrue(wagon.getDepoId()==wagon1.getDepoId());
    }

   @Test
    public void getWagonById() throws Exception{
        LOGGER.debug("test:getWagonById");
        Wagon wagon=wagonDao.getWagonById( 14176);
        Assert.assertNotNull(wagon);
    }

    @Test
    public void deleteWagon() throws Exception{
        LOGGER.debug("test:deleteWagon");
        int a=wagonDao.deleteWagon(14176);
        Assert.assertEquals(1,a);
    }


    @Test
    public void getWagonByDate() throws Exception{
        LOGGER.debug("test:getWagonByDate");
        List<Wagon> list=wagonDao.getWagonByDate(LocalDate.parse("2015-08-01"),LocalDate.parse("2016-09-01"));
        Assert.assertEquals(2,list.size());

    }


}