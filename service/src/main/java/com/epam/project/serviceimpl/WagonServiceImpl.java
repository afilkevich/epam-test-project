package com.epam.project.serviceimpl;

import com.epam.project.dao.WagonDao;
import com.epam.project.model.Wagon;
import com.epam.project.service.WagonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by master on 14.3.17.
 */
@Service
@Transactional
public class WagonServiceImpl implements WagonService {

    private static final Logger LOGGER= LogManager.getLogger();

    @Autowired
    private WagonDao wagonDao;

    public void setWagonDao(WagonDao wagonDao) {
        this.wagonDao = wagonDao;
    }

    @Override
    public List<Wagon> getAllWagonByDepo(Integer idDepo) throws DataAccessException {
        LOGGER.debug("getAllWagonByDepo",idDepo);
        Assert.notNull(idDepo,"must be not null");
        return wagonDao.getAllWagonByDepo(idDepo);
    }

    @Override
    public void addWagon(Wagon wagon) throws DataAccessException {

    }

    @Override
    public Wagon getWagonById(Integer id) throws DataAccessException {
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
    public Integer countWagonByDepo(Integer idDepo) throws DataAccessException {
        return null;
    }

    @Override
    public Integer sumOfSeatsByDepo(Integer idDepo) throws DataAccessException {
        return null;
    }

    @Override
    public List<Wagon> getWagonByDate(LocalDate from, LocalDate to) throws DataAccessException {
        return null;
    }
}
