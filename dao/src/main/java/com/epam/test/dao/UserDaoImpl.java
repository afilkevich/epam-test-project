package com.epam.test.dao;

import com.epam.test.model.User;
import com.epam.test.model.UserDao;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by master on 15.2.17.
 */
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER= LogManager.getLogger();



    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    String getAllUsersSql="select user_id, login, password, description from app_user";
    String getUserByIdSql="select user_id, login, password, description from app_user where user_id= :p_user_id";
    //@Value("${user.selectById")
    //String getUserByIdSql;
    String addUserSql="insert into app_user(login, password,description) values(:login, :password, :description)";
    String deleteUserSql="delete from app_user where user_id=:p_user_id";
    String updateUserSql="update app_user set  login=:login, password=:password, description=:description where user_id=:id";


    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public List<User> getAllUsers() {
        LOGGER.debug("getAllUser");

        return jdbcTemplate.query(getAllUsersSql,new UserRowMapper());
    }

    @Override
    public User getUserById(Integer userId) {
        LOGGER.debug("getUserById",userId);
        SqlParameterSource namedParametrs=new MapSqlParameterSource("p_user_id",userId);
        User user=namedParameterJdbcTemplate.queryForObject(getUserByIdSql,namedParametrs,new UserRowMapper());
        return user;
    }

    @Override
    public Integer addUser(User user) {
        LOGGER.debug("addUser",user);
        MapSqlParameterSource parameterSource=new MapSqlParameterSource();
        parameterSource.addValue("login",user.getLogin());
        parameterSource.addValue("password",user.getPassword());
        parameterSource.addValue("description",user.getDescription());
        KeyHolder keyHolder=new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addUserSql,parameterSource,keyHolder);

        return keyHolder.getKey().intValue();
    }


    @Override
    public void updateUser(User user) {
         LOGGER.debug("updateUser",user);


            MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",user.getUserId());
        mapSqlParameterSource.addValue("login",user.getLogin());
        mapSqlParameterSource.addValue("password",user.getPassword());
        mapSqlParameterSource.addValue("description",user.getDescription());



        namedParameterJdbcTemplate.update(updateUserSql,mapSqlParameterSource);

    }

    @Override
    public void deleteUser(Integer userId) {
        LOGGER.debug("deleteUser",userId);
        SqlParameterSource namedParametrs=new MapSqlParameterSource("p_user_id",userId);
        namedParameterJdbcTemplate.update(deleteUserSql,namedParametrs);
    }

    private class UserRowMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user=new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("description")

            );
            return user;
        }
    }
}
