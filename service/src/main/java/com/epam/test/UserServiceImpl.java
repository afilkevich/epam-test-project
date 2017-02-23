package com.epam.test;

import com.epam.test.UserService;
import com.epam.test.model.User;
import com.epam.test.model.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by master on 21.2.17.
 */
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER= LogManager.getLogger();

    @Autowired
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() throws DataAccessException {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Integer id) throws DataAccessException {
        LOGGER.debug("getUserByID()",id);
        Assert.notNull(id);

        return userDao.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) throws DataAccessException {
        LOGGER.debug("getUserByLogin()",login);
        Assert.hasLength(login,"user login shouldnot be null");
        return userDao.getUserbyLogin(login);

    }

    @Override
    public Integer addUser(User user) throws DataAccessException {
        Assert.notNull(user,"user should not be null");
        LOGGER.debug("addUser()",user);
        Assert.isNull(user.getUserId(),"id should be null");
        Assert.hasText(user.getLogin(),"login should not be null");
        Assert.hasText(user.getPassword(),"password should not be null");
        //Assert.isNull(userDao.getUserbyLogin(user.getLogin()),"login should be original");
        return userDao.addUser(user);

    }

    @Override
    public int updateUser(User user) throws DataAccessException {
        Assert.notNull(user,"user not be null");
        LOGGER.debug("updateUser",user);
        Assert.hasText(user.getLogin(),"login should not be null");
        Assert.hasText(user.getPassword(),"password should not be null");

        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(Integer id) throws DataAccessException {
        Assert.notNull(id);
        LOGGER.debug("deleteUser",id);

        return userDao.deleteUser(id);
    }
}
