package com.epam.test;

import com.epam.test.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by master on 21.2.17.
 */
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getAllUsers() throws DataAccessException {
        return null;
    }

    @Override
    public User getUserById(Integer id) throws DataAccessException {
        return null;
    }

    @Override
    public User getUserByLogin(String login) throws DataAccessException {
        return null;
    }

    @Override
    public Integer addUser(User user) throws DataAccessException {
        return null;
    }

    @Override
    public int updateUser(User user) throws DataAccessException {
        return 0;
    }

    @Override
    public int deleteUser(Integer id) throws DataAccessException {
        return 0;
    }
}
