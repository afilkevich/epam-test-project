package com.epam.project.model;

import java.util.Objects;

/**
 * Created by master on 30.3.17.
 */
public class DepoDTO {
    Integer id;
    String name;
    Integer count;
    Integer sum;

    public DepoDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepoDTO() {
    }

    public DepoDTO(Integer id, String name, Integer count, Integer sum) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepoDTO depoDTO = (DepoDTO) o;
        return Objects.equals(id, depoDTO.id) &&
                Objects.equals(name, depoDTO.name) &&
                Objects.equals(count, depoDTO.count) &&
                Objects.equals(sum, depoDTO.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, count, sum);
    }

    @Override
    public String toString() {
        return "DepoDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", sum=" + sum +
                '}';
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
