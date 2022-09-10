package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table
public class Artist {

  @Id
  @GeneratedValue
  private Long id;
  @Column(nullable = false)
  private String name;
  private LocalDate birthday;
}
