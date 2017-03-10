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

    private static final Depo depo=new Depo("Vitebsk wagon depo");

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
    public void getDepobyId() throws Exception
    {
       LOGGER.debug("test:getDepoById");
        Depo depo=depoAndWagonDao.getDepoById(1);
        Assert.assertTrue(depo.getId()==1);
    }

    @Test
    public  void addDepo() throws Exception{
        LOGGER.debug("test:addDepo");
        Integer id=depoAndWagonDao.addDepo(depo);
        Assert.assertNotNull(id);
    }

    @Test
    public void updateDepo() throws Exception{
        LOGGER.debug("test:updateDepo");
        Depo uDepo=depoAndWagonDao.getDepoById(1);
        uDepo.setName("Minsk's train depos");
        int a=depoAndWagonDao.updateDepo(uDepo);
        Assert.assertTrue(a==1);

    }

    @Test
    public void deleteDepo() throws Exception{
        LOGGER.debug("test:deleteDepo");
         int exp=depoAndWagonDao.deleteDepo(1);
        Assert.assertTrue(exp==1);
    }

    @Test
    public void getAllWagonByDepo() throws Exception {
        LOGGER.debug("test: getAllWagonByDepo");
        List<Wagon> wagons=depoAndWagonDao.getAllWagonByDepo(1);
        Assert.assertTrue(wagons.size()>0);

    }

}