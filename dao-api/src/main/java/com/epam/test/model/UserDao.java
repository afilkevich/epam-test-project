package com.epam.test.model;

import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by master on 15.2.17.
 */
public interface UserDao {

     List<User> getAllUsers() throws DataAccessException;

     User getUserById(Integer userId) throws DataAccessException;
    User getUserbyLogin(String login) throws DataAccessException;

     Integer addUser(User user) throws DataAccessException;

    int updateUser(User user) throws DataAccessException;

    int deleteUser(Integer userId) throws DataAccessException;

}
