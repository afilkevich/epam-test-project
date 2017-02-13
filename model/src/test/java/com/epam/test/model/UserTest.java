package com.epam.test.model;


import org.junit.Assert;

import org.junit.Test;

/**
 * Created by master on 13.2.17.
 */
public class UserTest {


    @Test
    public void setUserId() throws Exception {
        User user=new User();
        user.setUserId(11);
        Assert.assertEquals("id",(Integer)11,user.getUserId());

    }

    @Test
    public void setPassword() throws Exception {
        User user=new User();
        user.setPassword("a");
        Assert.assertEquals("password","a",user.getPassword());

    }

    @Test
    public void getLogin() throws Exception {
        User user=new User();
        user.setLogin("a");
        Assert.assertEquals("login","a",user.getLogin());

    }

    @Test
    public void setLogin() throws Exception {

        User user=new User();
        user.setLogin("as");
        Assert.assertEquals("login","as",user.getLogin());

    }

    @Test
    public void getDescription() throws Exception {
        User user=new User();
        user.setDescription("a");
        Assert.assertEquals("description","a",user.getDescription());

    }

    @Test
    public void setDescription() throws Exception {
        User user=new User();
        user.setDescription("a");
        Assert.assertEquals("description","a",user.getDescription());

    }

    @Test
    public void getUserId() throws Exception {
        User user=new User();
        user.setUserId(11);
        Assert.assertEquals("user id", (Integer)11,user.getUserId());

    }

    @Test
    public void getPassword() throws Exception {
        User user=new User();
        user.setPassword("ad");
        Assert.assertEquals("pasword","ad",user.getPassword());

    }





}