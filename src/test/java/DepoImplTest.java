//package com.epam.project.daoimpl;
//
//import com.epam.project.dao.DepoDao;
//import com.epam.project.model.Depo;
//import com.epam.project.model.DepoDTO;
//import com.epam.project.model.Wagon;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import  org.junit.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * Created by master on 9.3.17.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:test-spring-depo.xml"})
//@Transactional
//public class DepoImplTest {
//
//    private static final Depo depo=new Depo("Vitebsk wagon depo");
//
//    private static final Logger LOGGER= LogManager.getLogger();
//
//    @Autowired
//    DepoDao depoDao;
//
//    @Test
//    public void getAllDepo() throws Exception {
//        LOGGER.debug("test: getallDepo");
//        List<DepoDTO> depos=depoDao.getAllDepo();
//        Assert.assertTrue(depos.size()>0);
//    }
//
//    @Test
//    public void getDepobyId() throws Exception
//    {
//       LOGGER.debug("test:getDepoById");
//        Depo depo=depoDao.getDepoById(1);
//        Assert.assertTrue(depo.getId()==1);
//    }
//
//    @Test
//    public  void addDepo() throws Exception{
//        LOGGER.debug("test:addDepo");
//        Integer id=depoDao.addDepo(depo);
//        Assert.assertNotNull(id);
//    }
//
//    @Test
//    public void updateDepo() throws Exception{
//        LOGGER.debug("test:updateDepo");
//        Depo uDepo=depoDao.getDepoById(1);
//        uDepo.setName("Minsk's train depos");
//        int a=depoDao.updateDepo(uDepo);
//        Assert.assertTrue(a==1);
//
//    }
//
//    @Test
//    public void deleteDepo() throws Exception{
//        LOGGER.debug("test:deleteDepo");
//         int exp=depoDao.deleteDepo(1);
//        Assert.assertTrue(exp==1);
//    }
//
//
//
//}