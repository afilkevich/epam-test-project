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

    @Override
    public List<Depo> getAllDepo() throws DataAccessException {
        LOGGER.debug("getAllDepo");
        return depoDao.getAllDepo();
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
}
