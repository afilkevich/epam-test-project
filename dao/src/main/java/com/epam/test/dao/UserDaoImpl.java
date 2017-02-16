package com.epam.test.dao;

import com.epam.test.model.User;
import com.epam.test.model.UserDao;
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
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    String getAllUsersSql="select user_id, login, password, description from app_user";
    String getUserByIdSql="select user_id, login, password, description from app_user where user_id= :p_user_id";
    String addUserSql="insert into app_user(login, password,description) values(:login, :password, :description)";
    String deleteUserSql="delete from app_user where user_id=:p_user_id";
    String updateUserSql="update app_user set user_id=:user_id, login=:login, password=:password, description=:description where user_id=:p user_id";



    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public List<User> getAllUsers() {

        return jdbcTemplate.query(getAllUsersSql,new UserRowMapper());
    }

    @Override
    public User getUserById(Integer userId) {
        SqlParameterSource namedParametrs=new MapSqlParameterSource("p_user_id",userId);
        User user=namedParameterJdbcTemplate.queryForObject(getUserByIdSql,namedParametrs,new UserRowMapper());
        return user;
    }

    @Override
    public Integer addUser(User user) {
        MapSqlParameterSource parameterSource=new MapSqlParameterSource();
        parameterSource.addValue("login",user.getLogin());
        parameterSource.addValue("password",user.getPassword());
        parameterSource.addValue("description",user.getDescription());
        KeyHolder keyHolder=new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addUserSql,parameterSource,keyHolder);

        return keyHolder.getKey().intValue();
    }

    // it's have mistake
    @Override
    public void updateUser(User user) {

        Map<String,Object> userParametrs=new HashMap<>();
        userParametrs.put("id",user.getUserId());
        userParametrs.put("login",user.getLogin());
        userParametrs.put("password",user.getPassword());
        userParametrs.put("description", user.getDescription());


        namedParameterJdbcTemplate.update(updateUserSql,userParametrs);

    }

    @Override
    public void deleteUser(Integer userId) {
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
