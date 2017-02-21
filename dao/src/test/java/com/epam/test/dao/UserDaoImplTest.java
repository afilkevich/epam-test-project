package com.epam.test.dao;

import com.epam.test.model.User;
import com.epam.test.model.UserDao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.Assert.assertEquals;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional
public class UserDaoImplTest {

    private static final Logger LOGGER= LogManager.getLogger();

    static  final String USER_LOGIN_1="userLogin1";

    private  static final User user=new User("userLogin3","userPassword3");

    @Autowired
    UserDao userDao;

    @BeforeClass
    public  static void setUpBeforeClass() throws Exception{
        LOGGER.error("execute: setUpBeforeClass()");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        LOGGER.error("execute: tearDownAfterClass()");
    }

    @Before
    public void beforeTest(){
        LOGGER.error("execute: beforeTest");
    }

    @After
    public  void afterTest(){
        LOGGER.error("execute: afterTest");
    }



    @Test
    public void getAllUsersTest() throws Exception {
        LOGGER.debug("test: getAllUsers()");
        List<User> users=userDao.getAllUsers();
        assertTrue(users.size()>0);

    }

    @Test
    public void getUserByIdTest() throws Exception {
        LOGGER.debug("test: getUserById()");
        User user=userDao.getUserById(1);
        assertNotNull(user);

        assertEquals(USER_LOGIN_1,user.getLogin());

    }

    @Test
    public void getUserByLogin() throws Exception{
        LOGGER.debug("test: getUserByLogin");

        User user=userDao.getUserbyLogin(USER_LOGIN_1);
        assertNotNull(user);
        assertEquals(USER_LOGIN_1,user.getLogin());
    }

    @Test
    public void addUserTest() throws Exception {
        LOGGER.debug("test: addUserTest");

       List<User> users=userDao.getAllUsers();
        Integer oldsize=users.size();
        Integer idUser=userDao.addUser(user);
         assertNotNull(idUser);

        User newUser=userDao.getUserById(idUser);
        assertNotNull(newUser);
         assertTrue(newUser.getLogin().equals(user.getLogin()));
        assertTrue(newUser.getPassword().equals(user.getPassword()));

        users=userDao.getAllUsers();
        assertEquals(oldsize+1,users.size());


    }

    @Test
    public void updateUserTest() throws Exception {
        LOGGER.debug("execute; updateUserTest");
        User user=userDao.getUserById(1);
        user.setPassword("updatePass");
        user.setDescription("updateDescr");
        int count=userDao.updateUser(user);
        assertEquals(1,count);

        User updateUser=userDao.getUserById(1);
        assertTrue(user.getLogin().equals(updateUser.getLogin()));
        assertTrue(user.getPassword().equals(updateUser.getPassword()));
        assertTrue(user.getDescription().equals(updateUser.getDescription()));


    }

    @Test(expected = org.springframework.dao.DuplicateKeyException.class)
    public void testAddDuplicateUser() throws Exception{
        LOGGER.debug("test: testAddDuplicateUser()");
        User xUser=new User("userz","pass2");
        xUser.setUserId(1);
        userDao.addUser(xUser);
    }

    @Test
    public void deleteUserTest() throws Exception {
        LOGGER.debug("execute:deleteUserTest()");

        Integer idUser=userDao.addUser(user);
        assertNotNull(idUser);

        List<User> users=userDao.getAllUsers();
        Integer addusers=users.size();

        int count=userDao.deleteUser(idUser);
        assertEquals(1,count);

        users=userDao.getAllUsers();


        assertEquals(addusers-1,users.size());


    }

}