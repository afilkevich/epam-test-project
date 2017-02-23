package com.epam.test;

import com.epam.test.model.User;
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
 * Created by master on 21.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-test.xml"})
@Transactional
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    private static final String USER_LOGIN_1="userLogin1";



    @Test
    public void getAllUsers() throws Exception {
        List<User> users=userService.getAllUsers();
        assertEquals("",2,users.size());

    }

    @Test
    public void getUserById() throws Exception {
        User user=userService.getUserById(1);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getLogin());
        Assert.assertEquals((Integer) 1,user.getUserId());

    }

    @Test
    public void getUserByLogin() throws Exception {
        User user=userService.getUserByLogin(USER_LOGIN_1);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getLogin());
        Assert.assertEquals(USER_LOGIN_1,user.getLogin());


    }


    @Test
    public void addUser() throws Exception {
        User user=new User("us3","pass3");
        Assert.assertNull(user.getUserId());
        Integer id=userService.addUser(user);
        Assert.assertNotNull(id);
    }

    @Test
    public void updateUser() throws Exception {
        User user=userService.getUserByLogin(USER_LOGIN_1);
        user.setLogin("loginNew1");
        int count=userService.updateUser(user);
        Assert.assertEquals(1,count);
        user=userService.getUserByLogin("loginNew1");
        Assert.assertNotNull(user);

    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserNull(){
        userService.addUser(new User(null,"pass"));
    }

    @Test
    public void deleteUser() throws Exception {
        List<User> users=userService.getAllUsers();
        int oldsize=users.size();

        userService.deleteUser(1);
        users=userService.getAllUsers();
        Assert.assertEquals(oldsize-1,users.size());


    }

}