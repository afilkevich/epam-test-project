package com.epam.project.service;

import com.epam.project.model.Depo;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by master on 14.3.17.
 */
public interface DepoService {
    List<Depo> getAllDepo() throws DataAccessException;
    Depo getDepoById(Integer id) throws DataAccessException;
    Integer addDepo(Depo depo) throws DataAccessException;
    int updateDepo(Depo depo) throws DataAccessException;
    int deleteDepo(Integer id) throws DataAccessException;
}
