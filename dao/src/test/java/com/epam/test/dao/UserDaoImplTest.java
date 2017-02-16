package com.epam.test.dao;

import com.epam.test.model.User;
import com.epam.test.model.UserDao;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
public class UserDaoImplTest {
    @Autowired
    UserDao userDao;


    @Test
    public void getAllUsers() throws Exception {
        List<User> users=userDao.getAllUsers();
        assertTrue(users.size()==2);

    }

    @Test
    public void getUserById() throws Exception {
        User user=userDao.getUserById(1);
        assertNotNull(user);
        System.out.println(user);
        assertEquals("userLogin1",user.getLogin());

    }

    @Test
    public void addUser() throws Exception {
        User user=new User(3,"userLogin3","userPassword3","");
        Integer id=userDao.addUser(user);
        User userDb=userDao.getUserById(3);
        assertEquals(id,userDb.getUserId());


    }

    @Test
    public void updateUser() throws Exception {
        User user=userDao.getUserById(1);
        String oldPass=user.getPassword();
        user.setPassword("newPAss");
        userDao.updateUser(user);
        assertNotEquals(oldPass,userDao.getUserById(1).getPassword());

    }

    @Test
    public void deleteUser() throws Exception {
        userDao.deleteUser(1);
        TestCase.assertEquals(null,userDao.getUserById(1));


    }

}