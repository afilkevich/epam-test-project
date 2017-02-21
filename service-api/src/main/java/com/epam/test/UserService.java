package com.epam.test;

import com.epam.test.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by master on 20.2.17.
 */
public interface UserService {

    List<User> getAllUsers() throws DataAccessException;

    User getUserById(Integer id) throws DataAccessException;

    User getUserByLogin(String login) throws DataAccessException;

    Integer addUser(User user) throws DataAccessException;

    int updateUser(User user) throws DataAccessException;

    int deleteUser(Integer id) throws DataAccessException;
}
