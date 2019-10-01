package com.epam.project.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by master on 30.3.17.
 */
@Data
@Builder
public class DepoDTO {
    private Integer id;
    private String name;
    private Integer count;
    private Integer sum;

}
