package com.epam.project.daoimpl;

import com.epam.project.dao.DepoAndWagonDao;
import com.epam.project.model.Depo;
import com.epam.project.model.Wagon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by master on 9.3.17.
 */
public class DepoAndWagonDaoImpl implements DepoAndWagonDao {

    private static final Logger LOGGER= LogManager.getLogger();
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public DepoAndWagonDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    

    @Override
    public List<Depo> getAllDepo() throws DataAccessException {
        return null;
    }

    @Override
    public Depo getDepoById(Integer id) throws DataAccessException {
        return null;
    }

    @Override
    public Depo getDepobyName(String name) throws DataAccessException {
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
    public List<Wagon> getAllWagonByDepo(int depoId) throws DataAccessException {
        return null;
    }

    @Override
    public Wagon getWagonById(Integer id) throws DataAccessException {
        return null;
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
    public int deleteWagon(Integer idWagon) throws DataAccessException {
        return 0;
    }

    @Override
    public List<Wagon> getAllWagonByDate(LocalDate from, LocalDate to) throws DataAccessException {
        return null;
    }

    @Override
    public int getCountOfWagonByDepo(int depoId) throws DataAccessException {
        return 0;
    }

    @Override
    public int getSumofSeatByDepo(int depoId) throws DataAccessException {
        return 0;
    }
}
