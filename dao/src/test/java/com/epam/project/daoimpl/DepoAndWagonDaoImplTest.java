package com.epam.project.daoimpl;

import com.epam.project.dao.DepoAndWagonDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by master on 9.3.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional
public class DepoAndWagonDaoImplTest {

    private static final Logger LOGGER= LogManager.getLogger();

    @Autowired
    DepoAndWagonDao depoAndWagonDao;

    @Test
    public void getAllDepo() throws Exception {

    }

}