package com.epam.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.epam.project.dao.WagonDao;
import com.epam.project.model.Wagon;
import org.springframework.stereotype.Component;

/**
 * Created by alexander Dao-wagon implementation
 */
@Component
@Log4j2
public class WagonDaoImpl implements WagonDao {

    private static final Logger LOGGER = LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static final String WAGON_ID = "wagon_id";
    static final String WAGON_TYPE = "type";
    static final String WAGON_ID_DEPO = "d_id";
    static final String WAGON_SEATS = "count_seats";
    static final String WAGON_DATE = "date_build";

    @Value("${wagon.select}")
    String getAllWagonByDepoSql;
    @Value("${wagon.insert}")
    String addWagonSql;
    @Value("${wagon.selectbyid}")
    String getWagonIdSql;
    @Value("${wagon.update}")
    String updateWagonSql;
    @Value("${wagon.delete}")
    String deleteWagonSql;
    @Value("${wagon.selectbydate}")
    String selectByDateSql;
    @Value("${wagon.selectall}")
    String selectAllWagon;

    public WagonDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

//    /**
//     * Returns the list of wagon with the specified IdDepo from database.
//     * @param id
//     *            id of the depo which wagons return
//     * @return the List of Wagon from ResultSet
//     */
//    @Override
//    public List<Wagon> getAllWagonByDepo(Integer id) throws DataAccessException {
//        LOGGER.debug("getAllWagonByDepo", id);
//        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("d_id", id);
//        return namedParameterJdbcTemplate.query(getAllWagonByDepoSql, sqlParameterSource, new WagonRowMapper());
//    }

    /**
     * In case when we can have all Wagon we use this function.
     * @return List of Wagon from ResultSet of SQL query.
     */
    @Override
    public List<Wagon> getAllWagon() throws DataAccessException {
        LOGGER.debug("getAllWagon");
        return jdbcTemplate.query(selectAllWagon, new WagonRowMapper());
    }

//    /**
//     * Insert specified wagon to the database
//     * @param wagon
//     *            wagon to be inserted to the database
//     */
//    @Override
//    public void addWagon(Wagon wagon) throws DataAccessException {
//        LOGGER.debug("addWagon", wagon);
//        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
//        sqlParameterSource.addValue("wagon_id", wagon.getId());
//        sqlParameterSource.addValue("type", wagon.getType());
//        sqlParameterSource.addValue("d_id", wagon.getDepoId());
//        sqlParameterSource.addValue("count_seats", wagon.getCountOfSeat());
//        sqlParameterSource.addValue("date_build", wagon.getDateOfBuild());
//
//        namedParameterJdbcTemplate.update(addWagonSql, sqlParameterSource);
//    }
//
//    /**
//     * Returns the wagon with the specified Id from database.
//     * @param id
//     *            id of the wagon to return
//     * @return the wagon with the specified Id from the database
//     */
//    @Override
//    public Wagon getWagonById(Integer id) throws DataAccessException {
//        LOGGER.debug("getwagonById", id);
//        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
//        Wagon wagon =
//                namedParameterJdbcTemplate.queryForObject(getWagonIdSql, sqlParameterSource, new WagonRowMapper());
//        return wagon;
//    }
//
//    /**
//     * Replaces the wagon in the database with the specified wagon.
//     * @param wagon
//     *            to be updated in the database
//     */
//    @Override
//    public int updateWagon(Wagon wagon) throws DataAccessException {
//        LOGGER.debug("updateWagon", wagon);
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue("wagon_id", wagon.getId());
//        mapSqlParameterSource.addValue("type", wagon.getType());
//        mapSqlParameterSource.addValue("d_id", wagon.getDepoId());
//        mapSqlParameterSource.addValue("count_seats", wagon.getCountOfSeat());
//        mapSqlParameterSource.addValue("date_build", wagon.getDateOfBuild());
//        return namedParameterJdbcTemplate.update(updateWagonSql, mapSqlParameterSource);
//    }
//
//    /**
//     * Delete wagon from database
//     * @param id
//     *            of wagon
//     */
//    @Override
//    public int deleteWagon(Integer id) throws DataAccessException {
//        LOGGER.debug("deleteWagon", id);
//        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
//        return namedParameterJdbcTemplate.update(deleteWagonSql, sqlParameterSource);
//    }
//
//    /**
//     * In case when we can have all entity of Wagon which build in duration .
//     * @param from
//     *            localdate which begin build
//     * @param to
//     *            localdate which finish build
//     * @return List of Wagon from ResultSet of SQL query.
//     */
//    @Override
//    public List<Wagon> getWagonByDate(LocalDate from, LocalDate to) throws DataAccessException {
//        LOGGER.debug("getWagonByDate", from, to);
//        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
//        sqlParameterSource.addValue("from", from);
//        sqlParameterSource.addValue("to", to);
//        return namedParameterJdbcTemplate.query(selectByDateSql, sqlParameterSource, new WagonRowMapper());
//    }

    /**
     * Mapper for NamedParameterJdbcTemplate for tables of Wagon. In case when we can have only one
     * entity of Wagon we return wagon from @wagon field.
     */
    private class WagonRowMapper implements RowMapper<Wagon> {
        @Override
        public Wagon mapRow(ResultSet resultSet, int i) throws SQLException {
            //@formatter:off
           return Wagon.builder()
                   .id(resultSet.getInt(WAGON_ID))
                   .type(resultSet.getString(WAGON_TYPE))
                   .depoId(resultSet.getInt(WAGON_ID_DEPO)).
                   countOfSeat(resultSet.getInt(WAGON_SEATS))
                   .dateOfBuild(LocalDate.parse(resultSet.getString(WAGON_DATE)))
                   .build();
           //@formatter:on

        }
    }
}
