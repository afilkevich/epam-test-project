package com.epam.project.daoimpl;

import com.epam.project.dao.DepoAndWagonDao;
import com.epam.project.model.Depo;
import com.epam.project.model.Wagon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by master on 9.3.17.
 */
public class DepoAndWagonImpl implements DepoAndWagonDao {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    static final String DEPO_ID="depo_id";
    static final String DEPO_NAME="name";

     @Value("${depo.select}")
    String getAllDepoSql;

    public DepoAndWagonImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Depo> getAllDepo() throws DataAccessException {
        return jdbcTemplate.query(getAllDepoSql,new DepoRowMapper());
    }

    @Override
    public List<Wagon> getAllWagonByDepo(Integer id) throws DataAccessException {
        return null;
    }
    
    private class DepoRowMapper implements RowMapper<Depo> {
        @Override
        public Depo mapRow(ResultSet resultSet, int i) throws SQLException {
            Depo depo=new Depo(
                    resultSet.getInt(DEPO_ID),
                    resultSet.getString(DEPO_NAME)
            );
            return depo;
        }
    }
}
