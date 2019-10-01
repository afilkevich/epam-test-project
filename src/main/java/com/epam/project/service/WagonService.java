package com.epam.project.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.epam.project.model.Wagon;

/**
 * Created by master on 14.3.17.
 */
public interface WagonService {
    List<Wagon> getAllWagonByDepo(Integer idDepo) throws DataAccessException;
    List<Wagon> getAllWagon() throws DataAccessException;
    Integer addWagon(Wagon wagon) throws DataAccessException;
    Wagon getWagonById(Integer id) throws DataAccessException;
    int updateWagon(Wagon wagon) throws DataAccessException;
    int deleteWagon(Integer id) throws DataAccessException;
    List<Wagon> getWagonByDate(LocalDate from, LocalDate to) throws DataAccessException;

}
