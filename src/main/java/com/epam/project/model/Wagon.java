package com.epam.project.model;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

/**
 * Created by master on 5.3.17.
 */
@Data
@Builder
public class Wagon {
    private Integer id;
    private String type;
    private int depoId;
    private int countOfSeat;
    private LocalDate dateOfBuild;
}
