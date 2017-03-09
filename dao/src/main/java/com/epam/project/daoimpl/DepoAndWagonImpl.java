package com.epam.project.daoimpl;

import com.epam.project.dao.DepoAndWagonDao;
import com.epam.project.model.Depo;
import com.epam.project.model.Wagon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by master on 9.3.17.
 */
public class DepoAndWagonImpl implements DepoAndWagonDao {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static final String DEPO_ID="depo_id";
    static final String DEPO_NAME="name";
    static final String WAGON_ID="wagon_id";
    static final String WAGON_TYPE="type";
    static final String WAGON_ID_DEPO="d_id";
    static final String WAGON_SEATS="count_seats";
    static final String WAGON_DATE="date_build";

     @Value("${depo.select}")
    String getAllDepoSql;
    @Value("${wagon.select}")
    String getAllWagonByDepoSql;

    public DepoAndWagonImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Depo> getAllDepo() throws DataAccessException {
        return jdbcTemplate.query(getAllDepoSql,new DepoRowMapper());
    }

    @Override
    public Depo getDepoById(Integer id) throws DataAccessException {
        return null;
    }

    @Override
    public Integer addDepo(Depo depo) throws DataAccessException {
        return null;
    }

    @Override
    public int updateDepo(Depo depo) throws DataAccessException {
        return 0;
    }

    @Override
    public int deleteDepo(Integer id) throws DataAccessException {
        return 0;
    }

    @Override
    public List<Wagon> getAllWagonByDepo(Integer id) throws DataAccessException {
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource("d_id",id);
        return namedParameterJdbcTemplate.query(getAllWagonByDepoSql,sqlParameterSource,new WagonRowMapper());
    }

    @Override
    public Integer addWagon(Wagon wagon) throws DataAccessException {
        return null;
    }

    @Override
    public int updateWagon(Wagon wagon) throws DataAccessException {
        return 0;
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
    private class WagonRowMapper implements RowMapper<Wagon>{

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
