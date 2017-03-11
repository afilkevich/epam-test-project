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


    @Autowired
    WagonDao wagonDao;

    @Test
    public void getAllWagonByDepo() throws Exception {
        LOGGER.debug("test: getAllWagonByDepo");
        List<Wagon> wagons=wagonDao.getAllWagonByDepo(1);
        Assert.assertTrue(wagons.size()>0);

    }

}