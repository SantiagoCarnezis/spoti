package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class Artist {

  private String name;
  private LocalDate birthday;

}
