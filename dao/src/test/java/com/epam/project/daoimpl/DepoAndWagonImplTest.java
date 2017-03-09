package com.epam.project.daoimpl;

import com.epam.project.dao.DepoAndWagonDao;
import com.epam.project.model.Depo;
import com.epam.project.model.Wagon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import  org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.aop.scope.ScopedProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by master on 9.3.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional
public class DepoAndWagonImplTest {

    private static final Logger LOGGER= LogManager.getLogger();

    @Autowired
    DepoAndWagonDao depoAndWagonDao;

    @Test
    public void getAllDepo() throws Exception {
        LOGGER.debug("test: getallDepo");
        List<Depo> depos=depoAndWagonDao.getAllDepo();
        Assert.assertTrue(depos.size()>0);
    }

    @Test
    public void getAllWagonByDepo() throws Exception {
        LOGGER.debug("test: getAllWagonByDepo");
        List<Wagon> wagons=depoAndWagonDao.getAllWagonByDepo(1);
        Assert.assertTrue(wagons.size()>0);

    }
}