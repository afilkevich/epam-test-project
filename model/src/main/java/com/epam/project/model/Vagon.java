package com.epam.project.model;

import java.util.Date;

/**
 * Created by master on 5.3.17.
 */
public class Vagon {
    Integer id;
    String type;
    int countOfSeat;
    Date dateOfBuilder;

    public Vagon(Integer id, String type, int countOfSeat, Date dateOfBuilder) {
        this.id = id;
        this.type = type;
        this.countOfSeat = countOfSeat;
        this.dateOfBuilder = dateOfBuilder;
    }

    public Vagon() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCountOfSeat() {
        return countOfSeat;
    }

    public void setCountOfSeat(int countOfSeat) {
        this.countOfSeat = countOfSeat;
    }

    public Date getDateOfBuilder() {
        return dateOfBuilder;
    }

    public void setDateOfBuilder(Date dateOfBuilder) {
        this.dateOfBuilder = dateOfBuilder;
    }
}
