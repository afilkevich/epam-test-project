package com.epam.project.dao;

import com.epam.project.model.Wagon;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by master on 11.3.17.
 */
public interface WagonDao {
    List<Wagon> getAllWagonByDepo(Integer idDepo) throws DataAccessException;
    void addWagon(Wagon wagon) throws DataAccessException;
    Wagon getWagonById(Integer id) throws DataAccessException;
    int updateWagon(Wagon wagon) throws DataAccessException;
    int deleteWagon(Integer id) throws DataAccessException;
    Integer countWagonByDepo(Integer idDepo) throws DataAccessException;
    Integer sumOfSeatsByDepo(Integer idDepo) throws DataAccessException;
    List<Wagon> getWagonByDate(LocalDate from, LocalDate to) throws DataAccessException;
}
