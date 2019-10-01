package com.epam.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.epam.project.model.Depo;
import com.epam.project.model.DepoDTO;

import lombok.extern.log4j.Log4j2;

/**
 * Created by alexander Dao-depo implementation
 */
@Log4j2
public class DepoDaoImpl implements DepoDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static final String DEPO_ID = "depo_id";
    static final String DEPO_NAME = "name";
    static final String DEPO_COUNT = "count_wagon";
    static final String DEPO_SUM = "sum_seats";

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
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * In case when we can have more then one entity of Depo we use this function.
     * @return List of Depo from ResultSet of SQL query.
     */
    @Override
    public List<DepoDTO> getAllDepo() throws DataAccessException {
        log.debug("getallDepo");
        return jdbcTemplate.query(getAllDepoSql, new DepoDTORowMapper());
    }

    /**
     * Returns the depo with the specified Id from database.
     * @param id
     *            id of the depo to return
     * @return the depo with the specified Id from the database
     */
    @Override
    public Depo getDepoById(Integer id) throws DataAccessException {
        log.debug("getDepoById", id);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        Depo depo = namedParameterJdbcTemplate.queryForObject(getDepoById, sqlParameterSource, new DepoRowMapper());
        return depo;
    }

    /**
     * Insert specified depo to the database
     * @param depo
     *            depo to be inserted to the database
     * @return id of depo in database
     */
    @Override
    public Integer addDepo(Depo depo) throws DataAccessException {
        log.debug("addDepo", depo);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", depo.getId());
        mapSqlParameterSource.addValue("name", depo.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addDepo, mapSqlParameterSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * Replaces the depo in the database with the specified depo.
     * @param depo
     *            to be updated in the database
     */
    @Override
    public int updateDepo(Depo depo) throws DataAccessException {
        log.debug("updateDepo", depo);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", depo.getId());
        mapSqlParameterSource.addValue("name", depo.getName());
        return namedParameterJdbcTemplate.update(updateDepo, mapSqlParameterSource);
    }

    /**
     * Delete depo from database
     * @param id
     *            of depo
     */
    @Override
    public int deleteDepo(Integer id) throws DataAccessException {
        log.debug("deletedepo", id);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(deleteCopyWagons, sqlParameterSource);
        return namedParameterJdbcTemplate.update(deleteDepo, sqlParameterSource);
    }

    /**
     * Mapper for NamedParameterJdbcTemplate for tables of Depo. In case when we can have only one
     * entity of Depo we return depo from @depo field.
     */
    private class DepoRowMapper implements RowMapper<Depo> {
        @Override
        public Depo mapRow(ResultSet resultSet, int i) throws SQLException {
         //@formatter:off
           return  Depo.builder()
                   .id(resultSet.getInt(DEPO_ID))
                   .name(resultSet.getString(DEPO_NAME))
                   .build();
           //@formatter:on

        }
    }

    private class DepoDTORowMapper implements RowMapper<DepoDTO> {

        @Override
        public DepoDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            //@formatter:off
            return DepoDTO.builder()
                    .id(resultSet.getInt(DEPO_ID))
                    .name(resultSet.getString(DEPO_NAME))
                    .count(resultSet.getInt(DEPO_COUNT))
                    .sum(resultSet.getInt(DEPO_SUM))
                    .build();
            //@formatter:on
        }
    }

}
