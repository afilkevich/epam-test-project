package com.epam.project.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.epam.project.model.Depo;
import com.epam.project.model.DepoDTO;

/**
 * Created by master on 14.3.17.
 */
public interface DepoService {
    List<DepoDTO> getAllDepo() throws DataAccessException;

    Depo getDepoById(Integer id) throws DataAccessException;

    Integer addDepo(Depo depo) throws DataAccessException;

    int updateDepo(Depo depo) throws DataAccessException;

    int deleteDepo(Integer id) throws DataAccessException;
}
