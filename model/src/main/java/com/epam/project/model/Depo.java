package com.epam.project.model;

import java.util.List;

/**
 * Created by master on 5.3.17.
 */
public class Depo {
    Integer id;
    String name;
    List<Vagon> vagons;

    public Depo() {
    }

    public Depo(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vagon> getVagons() {
        return vagons;
    }

    public void setVagons(List<Vagon> vagons) {
        this.vagons = vagons;
    }
}
