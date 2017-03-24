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

    /**
     * Returns the list of wagon with the specified IdDepo from database.
     * @param idDepo id of the depo which wagons return
     * @return the List of Wagon from database
     */
    @Override
    public List<Wagon> getAllWagonByDepo(Integer idDepo) throws DataAccessException {
        LOGGER.debug("getAllWagonByDepo",idDepo);
        Assert.notNull(idDepo,"must be not null");
        return wagonDao.getAllWagonByDepo(idDepo);
    }

    /**
     * In case when we can have all  Wagon we use this function.
     * @return List of Wagon  from database.
     */
    @Override
    public List<Wagon> getAllWagon() throws DataAccessException {
        LOGGER.debug("getAllWagon");
        return wagonDao.getAllWagon();
    }

    /**
     * Insert specified wagon to the database
     * @param wagon wagon to be inserted to the database
     */
    @Override
    public Integer addWagon(Wagon wagon) throws DataAccessException {
        LOGGER.debug("addWagon", wagon);
        Assert.notNull(wagon,"wagon musn't be null");
        Assert.notNull(wagon.getId(),"wagon must be have id");
        Assert.hasText(wagon.getType(),"wagon must have id");
        Assert.notNull(wagon.getDateOfBuilder(),"must have date");
        try {
            wagonDao.addWagon(wagon);
        }
        catch (Exception e){
            LOGGER.debug("addWagon have exception",e);
            throw new IllegalArgumentException();
        }
        return wagon.getId();
    }
    /**
     * Returns the wagon with the specified Id from database.
     * @param id id of the wagon to return
     * @return the wagon with the specified Id from the database
     */
    @Override
    public Wagon getWagonById(Integer id) throws DataAccessException {
        LOGGER.debug("getwagonById",id);
        Assert.notNull(id);
        Assert.isTrue(id>0);
        Wagon wagon;
        try {
            wagon=wagonDao.getWagonById(id);
        }
        catch (Exception e){
            LOGGER.debug("getWagonById have Exception",e);
            throw new IllegalArgumentException();
        }
        return wagon;
    }

    /**
     * Replaces the wagon in the database with the specified wagon.
     * @param wagon to be updated in the database
     */
    @Override
    public int updateWagon(Wagon wagon) throws DataAccessException {
        LOGGER.debug("updateWagon",wagon);
        Assert.notNull(wagon);
        Assert.isTrue(wagon.getId()>0);
        Assert.hasText(wagon.getType(),"wagon must have type");
        Assert.notNull(wagon.getDateOfBuilder(),"wagon must have date of build");
        try {
            Wagon testWagon=wagonDao.getWagonById(wagon.getId());
        }
        catch (Exception e){
            LOGGER.debug("updateWagon have exception",wagon);
            throw new IllegalArgumentException();
        }
        return wagonDao.updateWagon(wagon);
    }

    /**
     * Delete wagon from database
     * @param id of wagon
     */
    @Override
    public int deleteWagon(Integer id) throws DataAccessException {
        LOGGER.debug("deleteWagon",id);
        Assert.notNull(id);
        Assert.isTrue(id>0);
        try {
         Wagon  wagon=wagonDao.getWagonById(id);
        }
        catch (Exception e) {
            LOGGER.debug("deleteWAgon have exception",e);
            throw new IllegalArgumentException();
        }
        return wagonDao.deleteWagon(id);
    }

    /**
     * Returns the count of wagon in depo with the specified Id from database.
     * @param idDepo id of the depo to return
     * @return the count of wagon in depo with the specified Id from database
     */
    @Override
    public Integer countWagonByDepo(Integer idDepo) throws DataAccessException {
        LOGGER.debug("countWagonByDepo",idDepo);
        Assert.notNull(idDepo);
        Assert.isTrue(idDepo>0);
        int count=0;
        try {
            count=wagonDao.countWagonByDepo(idDepo);
        }
        catch (Exception e){
            LOGGER.debug("countWagonByDepo have exception",e);
            throw new IllegalArgumentException();
        }
        return count;
    }

     /**
     * Returns the sum of seats of wagons in depo with the specified Id from database.
     * @param idDepo id of the depo to return
     * @return Returns the summ of seats of wagons in depo with the specified Id from database.
     */
    @Override
    public Integer sumOfSeatsByDepo(Integer idDepo) throws DataAccessException {
        LOGGER.debug("sumOfSeatsByDepo",idDepo);
        Assert.notNull(idDepo);
        Assert.isTrue(idDepo>0);
        int sum=0;
        try {
            sum=wagonDao.sumOfSeatsByDepo(idDepo);
        }
        catch (Exception e){
            LOGGER.debug("sumOfSeatsByDepo have exception",e);
            throw new IllegalArgumentException();
        }
        return sum;
    }

    /**
     * In case when we can have all entity of Wagon which build in duration .
     * @param from localdate which begin build
     * @param to localdate which finish build
     * @return List of Wagon  from database.
     */
    @Override
    public List<Wagon> getWagonByDate(LocalDate from, LocalDate to) throws DataAccessException {
        LOGGER.debug("getWagonByDate",from,to);
        Assert.notNull(from);
        Assert.notNull(to);
        Assert.isTrue(from.isBefore(to));
        List<Wagon> wagons;
        try {
            wagons=wagonDao.getWagonByDate(from,to);
            Assert.notEmpty(wagons);
        }
        catch (Exception e){
            LOGGER.debug("gerWagonByDate have exception",e);
            throw new IllegalArgumentException();
            }
        return wagons;
    }
}
