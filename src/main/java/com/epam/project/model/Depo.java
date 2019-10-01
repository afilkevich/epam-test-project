package com.epam.project.model;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

/**
 * Created by master on 5.3.17.
 */
@Data
@Builder
public class Depo {
  private Integer id;
   private String name;

}
