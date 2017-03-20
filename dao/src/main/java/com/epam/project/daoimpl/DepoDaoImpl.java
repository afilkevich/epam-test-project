package com.epam.project.daoimpl;

import com.epam.project.dao.DepoDao;
import com.epam.project.model.Depo;
import com.epam.project.model.Wagon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by alexander
 * Dao-depo implementation
 */
public class DepoDaoImpl implements DepoDao {

    private static final Logger LOGGER= LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static final String DEPO_ID="depo_id";
    static final String DEPO_NAME="name";


     @Value("${depo.select}")
    String getAllDepoSql;
    @Value("${depo.selectbyid}")
    String getDepoById;
    @Value("${depo.add}")
    String addDepo;
    @Value("${depo.update}")
    String updateDepo;
    @Value("${depo.delete}")
    String deleteDepo;
    @Value("${depo.deletecopywagons}")
    String deleteCopyWagons;


    public DepoDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Depo> getAllDepo() throws DataAccessException {
        LOGGER.debug("getallDepo");
        return jdbcTemplate.query(getAllDepoSql,new DepoRowMapper());
    }

    @Override
    public Depo getDepoById(Integer id) throws DataAccessException {
        LOGGER.debug("getDepoById",id);
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource("id",id);
        Depo depo=namedParameterJdbcTemplate.queryForObject(getDepoById,sqlParameterSource,new DepoRowMapper());
        return depo;
    }

    @Override
    public Integer addDepo(Depo depo) throws DataAccessException {
        LOGGER.debug("addDepo",depo);
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",depo.getId());
        mapSqlParameterSource.addValue("name",depo.getName());
        KeyHolder keyHolder=new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addDepo,mapSqlParameterSource,keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int updateDepo(Depo depo) throws DataAccessException {
        LOGGER.debug("updateDepo",depo);
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",depo.getId());
        mapSqlParameterSource.addValue("name",depo.getName());
        return namedParameterJdbcTemplate.update(updateDepo,mapSqlParameterSource);
    }

    @Override
    public int deleteDepo(Integer id) throws DataAccessException {
        LOGGER.debug("deletedepo",id);
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource("id",id);
        namedParameterJdbcTemplate.update(deleteCopyWagons,sqlParameterSource);
        return namedParameterJdbcTemplate.update(deleteDepo,sqlParameterSource);
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
