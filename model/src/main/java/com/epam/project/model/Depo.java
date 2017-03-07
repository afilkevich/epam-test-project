package com.epam.project.model;


import java.util.Objects;

/**
 * Created by master on 5.3.17.
 */
public class Depo {
    Integer id;
    String name;


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


    @Override
    public String toString() {
        return "Depo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depo depo = (Depo) o;
        return Objects.equals(id, depo.id) &&
                Objects.equals(name, depo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
