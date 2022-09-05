package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table
public class Artist {

  @Id
  @GeneratedValue
  private int id;
  private String name;
  private LocalDate birthday;
}
