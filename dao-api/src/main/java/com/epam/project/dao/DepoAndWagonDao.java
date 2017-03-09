package com.epam.project.dao;

import com.epam.project.model.Depo;
import com.epam.project.model.Wagon;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by master on 9.3.17.
 */
public interface DepoAndWagonDao {
    List<Depo> getAllDepo() throws DataAccessException;
    List<Wagon> getAllWagonByDepo(Integer id) throws DataAccessException;
}
