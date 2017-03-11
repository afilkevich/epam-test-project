package com.epam.project.daoimpl;

import com.epam.project.dao.WagonDao;
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
 * Created by master on 11.3.17.
 */
public class WagonDaoImpl implements WagonDao {

    private static final Logger LOGGER= LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static final String WAGON_ID="wagon_id";
    static final String WAGON_TYPE="type";
    static final String WAGON_ID_DEPO="d_id";
    static final String WAGON_SEATS="count_seats";
    static final String WAGON_DATE="date_build";

    @Value("${wagon.select}")
    String getAllWagonByDepoSql;
    @Value("${wagon.insert}")
    String addWagonSql;
    @Value("${wagon.selectbyid}")
    String getWagonIdSql;
    @Value("${wagon.update}")
    String updateWagonSql;

    public WagonDaoImpl(DataSource dataSource) {
        jdbcTemplate =  new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Wagon> getAllWagonByDepo(Integer id) throws DataAccessException {
        LOGGER.debug("getAllWagonByDepo",id);
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource("d_id",id);
        return namedParameterJdbcTemplate.query(getAllWagonByDepoSql,sqlParameterSource,new WagonRowMapper());
    }

    @Override
    public void addWagon(Wagon wagon) throws DataAccessException {
        LOGGER.debug("addWagon",wagon);
        MapSqlParameterSource sqlParameterSource=new MapSqlParameterSource();
        sqlParameterSource.addValue("wagon_id",wagon.getId());
        sqlParameterSource.addValue("type",wagon.getType());
        sqlParameterSource.addValue("d_id",wagon.getDepoId());
        sqlParameterSource.addValue("count_seats",wagon.getCountOfSeat());
        sqlParameterSource.addValue("date_build",wagon.getDateOfBuilder());

        namedParameterJdbcTemplate.update(addWagonSql,sqlParameterSource);
    }

    @Override
    public Wagon getWagonById(Integer id) throws DataAccessException {
        LOGGER.debug("getwagonById",id);
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource("id",id);
        Wagon wagon=namedParameterJdbcTemplate.queryForObject(getWagonIdSql,sqlParameterSource,new WagonRowMapper());
       return wagon;
    }

    @Override
    public int updateWagon(Wagon wagon) throws DataAccessException {
        LOGGER.debug("updateWagon",wagon);
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("wagon_id",wagon.getId());
        mapSqlParameterSource.addValue("type",wagon.getType());
        mapSqlParameterSource.addValue("d_id",wagon.getDepoId());
        mapSqlParameterSource.addValue("count_seats",wagon.getCountOfSeat());
        mapSqlParameterSource.addValue("date_build",wagon.getDateOfBuilder());
        return namedParameterJdbcTemplate.update(updateWagonSql,mapSqlParameterSource);
    }

    @Override
    public int deleteWagon(Integer id) throws DataAccessException {
        return 0;
    }

    @Override
    public int countWagonByDepo(Integer idDepo) throws DataAccessException {
        return 0;
    }

    @Override
    public int countOfSeatsByDepo(Integer idDepo) throws DataAccessException {
        return 0;
    }

    @Override
    public List<Wagon> getWagonByDate(LocalDate from, LocalDate to) throws DataAccessException {
        return null;
    }

    private class WagonRowMapper implements RowMapper<Wagon> {

        @Override
        public Wagon mapRow(ResultSet resultSet, int i) throws SQLException {
            Wagon wagon=new Wagon(
                    resultSet.getInt(WAGON_ID),
                    resultSet.getString(WAGON_TYPE),
                    resultSet.getInt(WAGON_ID_DEPO),
                    resultSet.getInt(WAGON_SEATS),
                    LocalDate.parse(resultSet.getString(WAGON_DATE))
            );
            return wagon;
        }
    }
}
