package com.epam.project.dao;

import com.epam.project.model.Depo;
import com.epam.project.model.Wagon;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by master on 9.3.17.
 */
public interface DepoAndWagonDao {

    List<Depo> getAllDepo() throws DataAccessException;
    Depo getDepoById(Integer id) throws DataAccessException;
    Depo getDepobyName(String name) throws DataAccessException;
    Integer addDepo(Depo depo) throws DataAccessException;
    int updateDepo(Depo depo) throws DataAccessException;
    int deleteDepo(Integer id) throws DataAccessException;

    List<Wagon> getAllWagonByDepo(int depoId) throws DataAccessException;
    Wagon getWagonById(Integer id) throws DataAccessException;
    Integer addWagon(Wagon wagon) throws DataAccessException;
    int updateWagon(Wagon wagon) throws DataAccessException;
    int deleteWagon(Integer idWagon) throws DataAccessException;

    List<Wagon> getAllWagonByDate(LocalDate from,LocalDate to) throws DataAccessException;
    int getCountOfWagonByDepo(int depoId) throws DataAccessException;
    int getSumofSeatByDepo(int depoId) throws DataAccessException;


}
