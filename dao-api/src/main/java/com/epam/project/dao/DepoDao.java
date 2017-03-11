package com.epam.project.dao;

import com.epam.project.model.Depo;
import com.epam.project.model.Wagon;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by master on 9.3.17.
 */
public interface DepoDao {
    List<Depo> getAllDepo() throws DataAccessException;
    Depo getDepoById(Integer id) throws DataAccessException;
    Integer addDepo(Depo depo) throws DataAccessException;
    int updateDepo(Depo depo) throws DataAccessException;
    int deleteDepo(Integer id) throws DataAccessException;


}
