package com.epam.test.client.rest.api;

import com.epam.test.client.exception.ServerDataAccessException;
import com.epam.test.model.User;

import java.util.List;

/**
 * Created by master on 28.2.17.
 */
public interface UsersConsumer {
    List<User> getAllUsers() throws ServerDataAccessException;

    User getUserById(Integer id) throws ServerDataAccessException;

    User getUserByLogin(String login) throws ServerDataAccessException;

    Integer addUser(User user) throws ServerDataAccessException;

    int updateUser(User user) throws ServerDataAccessException;

    int deleteUser(Integer id) throws ServerDataAccessException;
}
