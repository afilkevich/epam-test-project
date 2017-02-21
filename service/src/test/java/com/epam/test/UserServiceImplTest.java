package com.epam.test;

import org.junit.runner.RunWith;
import org.springframework.aop.scope.ScopedProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
    
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void getAllUsers() throws Exception {

    }

    @org.junit.Test
    public void getUserById() throws Exception {

    }

    @org.junit.Test
    public void getUserByLogin() throws Exception {

    }

    @org.junit.Test
    public void addUser() throws Exception {

    }

    @org.junit.Test
    public void updateUser() throws Exception {

    }

    @org.junit.Test
    public void deleteUser() throws Exception {

    }

}