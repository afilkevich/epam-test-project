package com.epam.project.serviceimpl;

import com.epam.project.dao.DepoDao;
import com.epam.project.model.Depo;
import com.epam.project.service.DepoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by master on 14.3.17.
 */
@Service
@Transactional
public class DepoServiceImpl implements DepoService {

    private static final Logger LOGGER= LogManager.getLogger();

    @Autowired
    private DepoDao depoDao;

    public void setDepoDao(DepoDao depoDao) {
        this.depoDao = depoDao;
    }

    /**
     * In case when we can have more then one entity of Depo we use this function.
     * @return List of Depo  from database
     */
    @Override
    public List<Depo> getAllDepo() throws DataAccessException {
        LOGGER.debug("getAllDepo");
        return depoDao.getAllDepo();
    }

    /**
     * Returns the depo with the specified Id from database.
     * @param id id of the depo to return
     * @return the depo with the specified Id from the database
     */
    @Override
    public Depo getDepoById(Integer id) throws DataAccessException {
        LOGGER.debug("getDepoById",id);
        Assert.notNull(id,"id nust not be null");
        Depo depo;
        try {
            depo=depoDao.getDepoById(id);
            Assert.hasText(depo.getName(),"depo must have name");
        }
        catch (Exception e){
            LOGGER.debug("getDepoById have error",e);
            throw new IllegalArgumentException();

        }
        return depo;
    }

    /**
     * Insert specified depo to the database
     * @param depo depo to be inserted to the database
     * @return id of depo in database
     */
    @Override
    public Integer addDepo(Depo depo) throws DataAccessException {
        LOGGER.debug("addDepo",depo);
        Assert.hasText(depo.getName(),"must have name");
        Integer id;
        try {
            id = depoDao.addDepo(depo);
        }
        catch (Exception e){
            LOGGER.debug("addDepo have exception",e);
            throw new IllegalArgumentException();
        }
        Assert.notNull(id);
        return id;
    }

    /**
    * Replaces the depo in the database with the specified depo.
    * @param depo to be updated in the database
     */
    @Override
    public int updateDepo(Depo depo) throws DataAccessException {
        LOGGER.debug("updateDepo",depo);
        Assert.notNull(depo,"depo must be not null");
        Assert.hasText(depo.getName(),"depo must have name");
        Assert.notNull(depo.getId(),"if update depo must have id");

        try {
            Depo depoTest=depoDao.getDepoById(depo.getId());
        }
        catch (Exception e){
            LOGGER.debug("have exception",e);
            throw new IllegalArgumentException();
        }
        return depoDao.updateDepo(depo);
    }

    /**
     * Delete depo from database
     * @param id of depo
     */
    @Override
    public int deleteDepo(Integer id) throws DataAccessException {
        Assert.notNull(id);
        Depo depo;
        try {
            depo=depoDao.getDepoById(id);
        }
        catch (Exception e){
            LOGGER.debug("have exeption",e);
            throw new IllegalArgumentException();
        }
        return depoDao.deleteDepo(id);
    }
}
